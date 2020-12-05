<template>
    <div class="Paint">
        <h1>{{ msg }}</h1>
    </div>
    <!-- the user interface code -->
    <div class="main">
        <!-- save and load buttons -->
        <div>
            <button class="opt" @click="save('JSON')" title="save json">Save Json</button>
            <button class="opt" @click="load('JSON')" title="load json">Load Json</button>
            <button class="opt" @click="save('XML')" title="save xml">Save XML</button>
            <button class="opt" @click="load('XML')" title="load xml">Load XML</button>
        </div>
        <!-- operations on board buttons -->
        <div>
            <button class="opt" @click="undo" title="undo">Undo</button>
            <button class="opt" @click="redo" title="redo">Redo</button>
            <button class="opt" @click="btnclear" title="clear screen">Clear</button>
        </div>
        <!-- drawing shapes buttons -->
        <div class="shapes">
            <button class="square" @click="setSquare" title="square"></button>
            <button class="rectangle" @click="setRectangle" title="rectangle"></button>
            <button class="circle" @click="setCircle" title="circle"></button>
            <button class="triangle" @click="setTriangle" title="triangle"></button>
            <button class="ellipse" @click="setEllipse" title="ellipse"></button>
            <button class="line" @click="setLine" title="line"></button>
        </div>
        <!-- operations on shapes button -->
        <div>
            <button class="move" @click="setMove" title="move" ></button>
            <button class="delete" @click="setDelete" title="delete"></button>
            <button class="resize" @click="setResize" title="resize"></button>
            <button class="copy" @click="setCopy" title="copy"></button>
        </div>
        <!-- color and stroke inputs -->
        <label class="label">select the fill color:</label>
        <input type="color" id="myColor" title="fill color"/>
        <label class="label">select the stroke color:</label>
        <input type="color" id="myStroke" title="stroke coloer"/>
        <label class="label">stroke width</label>
        <input type="number" id="strokeWidth" min="0" max="5" />
        <!-- drawing area -->
        <canvas
            id="myCanvas"
            width="1500"
            height="800"
            class="drawing-board"
            @click="setPoint"
            @mousedown="select"
            @mouseup="performOrder"
        ></canvas>
    </div>
</template>

<script>
import axios from "axios";
export default {
    name: "Paint",
    props: {
        msg: String
    },
    /* data used in the front-end project */
    data() {
        return {
            currBoardIndex: 0,
            shapes: [null],
            /* shapes structure that carry whole shape data */
            shapeStruct: {
                points: [],
                shapeType: "null",
                colour: "null",
                indexInBoard: "-1",
                stroke: "null",
                strokeWidth: "null",
                length: "null",
                width: "null",
                sideLength: "null",
                hRadius: "null",
                vRadius: "null",
                radius: "null"
            },
            operation: "null",
            numOfShapes: 0,
            canvas: null,
            selectedShape: false,
            selShape: "null",
            xBefMov: 0,
            yBefMov: 0,
            movedX: 0,
            movedY: 0,
            oder: "null",
            resizeRatio: 1
        };
    },
    mounted() {
        /* mounting our canvas */
        var c = document.getElementById("myCanvas");
        this.canvas = c.getContext("2d");
    },
    methods: {
        /* function to detect the selected point by mouse click using mouse event (e)*/
        async setPoint(e) {
            if (this.selectedShape == true) {
                var canvas = document.getElementById("myCanvas");
                var ctx = canvas.getContext("2d");
                var x = e.offsetX;
                var y = e.offsetY;
                ctx.beginPath();
                ctx.arc(x, y, 2, 0, 2 * Math.PI);
                ctx.fill();
                this.shapeStruct.points.push({ x: x, y: y });
                if (
                    (this.shapeStruct.points.length == 2 &&
                        this.shapeStruct.shapeType == "RECTANGLE") ||
                    (this.shapeStruct.points.length == 2 &&
                        this.shapeStruct.shapeType == "SQUARE") ||
                    (this.shapeStruct.points.length == 2 &&
                        this.shapeStruct.shapeType == "LINE") ||
                    (this.shapeStruct.points.length == 3 &&
                        this.shapeStruct.shapeType == "TRIANGLE") ||
                    (this.shapeStruct.points.length == 3 &&
                        this.shapeStruct.shapeType == "ELLIPSE")
                ) {
                    await this.sendRequest();
                } else if (
                    this.shapeStruct.points.length == 2 &&
                    this.shapeStruct.shapeType == "CIRCLE"
                ) {
                    this.shapeStruct.points.push(this.shapeStruct.points[1]);
                    await this.sendRequest();
                }
            }
        },
        /* function to draw the shape from the shape structure data that came from the back-end project */
        async drawShapes() {
            var canvas = document.getElementById("myCanvas");
            if (canvas.getContext) {
                var ctx = canvas.getContext("2d");
                ctx.fillStyle = this.shapeStruct.colour;
                ctx.strokeStyle = this.shapeStruct.stroke;
                switch (this.shapeStruct.shapeType) {
                    case "RECTANGLE":
                        ctx.fillRect(
                            this.shapeStruct.points[0].x,
                            this.shapeStruct.points[0].y,
                            this.shapeStruct.width,
                            this.shapeStruct.length
                        );
                        if (this.shapeStruct.strokeWidth > 0) {
                            ctx.lineWidth = this.shapeStruct.strokeWidth;
                            ctx.strokeRect(
                                this.shapeStruct.points[0].x,
                                this.shapeStruct.points[0].y,
                                this.shapeStruct.width,
                                this.shapeStruct.length
                            );
                        }
                        this.shapeStruct.shapeType = "RECTANGLE";
                        break;
                    case "SQUARE":
                        ctx.fillRect(
                            this.shapeStruct.points[0].x,
                            this.shapeStruct.points[0].y,
                            this.shapeStruct.width,
                            this.shapeStruct.length
                        );
                        if (this.shapeStruct.strokeWidth > 0) {
                            ctx.lineWidth = this.shapeStruct.strokeWidth;
                            ctx.strokeRect(
                                this.shapeStruct.points[0].x,
                                this.shapeStruct.points[0].y,
                                this.shapeStruct.width,
                                this.shapeStruct.length
                            );
                        }
                        this.shapeStruct.shapeType = "SQUARE";
                        break;
                    case "CIRCLE":
                        ctx.beginPath();
                        ctx.arc(
                            this.shapeStruct.points[0].x,
                            this.shapeStruct.points[0].y,
                            this.shapeStruct.radius,
                            0,
                            2 * Math.PI
                        );
                        ctx.fill();
                        if (this.shapeStruct.strokeWidth > 0) {
                            ctx.lineWidth = this.shapeStruct.strokeWidth;
                            ctx.stroke();
                        }
                        this.shapeStruct.shapeType = "CIRCLE";
                        break;
                    case "TRIANGLE":
                        ctx.beginPath();
                        ctx.moveTo(
                            this.shapeStruct.points[0].x,
                            this.shapeStruct.points[0].y
                        );
                        ctx.lineTo(
                            this.shapeStruct.points[1].x,
                            this.shapeStruct.points[1].y
                        );
                        ctx.lineTo(
                            this.shapeStruct.points[2].x,
                            this.shapeStruct.points[2].y
                        );
                        ctx.lineTo(
                            this.shapeStruct.points[0].x,
                            this.shapeStruct.points[0].y
                        );
                        ctx.fill();
                        if (this.shapeStruct.strokeWidth > 0) {
                            ctx.lineWidth = this.shapeStruct.strokeWidth;
                            ctx.stroke();
                        }
                        this.shapeStruct.shapeType = "TRIANGLE";
                        break;
                    case "ELLIPSE":
                        ctx.beginPath();
                        ctx.ellipse(
                            this.shapeStruct.points[0].x,
                            this.shapeStruct.points[0].y,
                            this.shapeStruct.hRadius,
                            this.shapeStruct.vRadius,
                            0,
                            0,
                            2 * Math.PI
                        );
                        ctx.fill();
                        if (this.shapeStruct.strokeWidth > 0) {
                            ctx.lineWidth = this.shapeStruct.strokeWidth;
                            ctx.stroke();
                        }
                        this.shapeStruct.shapeType = "ELLIPSE";
                        break;
                    case "LINE":
                        ctx.beginPath();
                        ctx.moveTo(
                            this.shapeStruct.points[0].x,
                            this.shapeStruct.points[0].y
                        );
                        ctx.lineWidth =
                            this.shapeStruct.strokeWidth > 0
                                ? this.shapeStruct.strokeWidth
                                : 1;
                        ctx.lineTo(
                            this.shapeStruct.points[1].x,
                            this.shapeStruct.points[1].y
                        );
                        ctx.stroke();
                        this.shapeStruct.shapeType = "LINE";
                        break;
                    default:
                }
            }
        },
        /* function that send the data from front-end to back-end to process the data ad draw the shapes */
        async sendRequest() {
            var color = document.getElementById("myColor");
            var stroke = document.getElementById("myStroke");
            var stWidth = document.getElementById("strokeWidth");
            this.shapeStruct.colour = color.value;
            this.shapeStruct.stroke = stroke.value;
            this.shapeStruct.strokeWidth = stWidth.value;
            const response = await axios.post("http://localhost:8095/shapes/", {
                shape: this.shapeStruct,
                operation: "CREATE"
            });
            this.shapes = response.data;
            this.clear();
            if (this.shapes.length != 0) {
                this.shapes.forEach(element => {
                    if (element != null) {
                        this.shapeStruct = element;
                        this.drawShapes();
                    }
                });
            }
            this.selectedShape = false;
            this.currBoardIndex++;
        },
        /* function to select the shape to perform the shape operations on it using mouse event (e) */
        async select(e) {
            var x = e.offsetX;
            var y = e.offsetY;
            var c = document.getElementById("myCanvas");
            var ctx = c.getContext("2d");
            for (var i = this.shapes.length - 1; i >= 0; --i) {
                ctx.beginPath();
                if (this.shapes[i] == null) continue;
                switch (this.shapes[i].shapeType) {
                    case "RECTANGLE":
                    case "SQUARE":
                        ctx.rect(
                            this.shapes[i].points[0].x,
                            this.shapes[i].points[0].y,
                            this.shapes[i].width,
                            this.shapes[i].length
                        );
                        break;
                    case "CIRCLE":
                        ctx.arc(
                            this.shapes[i].points[0].x,
                            this.shapes[i].points[0].y,
                            this.shapes[i].radius,
                            0,
                            2 * Math.PI
                        );
                        break;
                    case "TRIANGLE":
                        ctx.moveTo(
                            this.shapes[i].points[0].x,
                            this.shapes[i].points[0].y
                        );
                        ctx.lineTo(
                            this.shapes[i].points[1].x,
                            this.shapes[i].points[1].y
                        );
                        ctx.lineTo(
                            this.shapes[i].points[2].x,
                            this.shapes[i].points[2].y
                        );
                        ctx.lineTo(
                            this.shapes[i].points[0].x,
                            this.shapes[i].points[0].y
                        );
                        break;
                    case "ELLIPSE":
                        ctx.ellipse(
                            this.shapes[i].points[0].x,
                            this.shapes[i].points[0].y,
                            this.shapes[i].hRadius,
                            this.shapes[i].vRadius,
                            0,
                            0,
                            2 * Math.PI
                        );
                        break;
                    case "LINE":
                        ctx.moveTo(
                            this.shapes[i].points[0].x,
                            this.shapes[i].points[0].y
                        );
                        ctx.lineWidth =
                            this.shapes[i].strokeWidth > 0
                                ? this.shapes[i].strokeWidth
                                : 1;
                        ctx.lineTo(
                            this.shapes[i].points[1].x,
                            this.shapes[i].points[1].y
                        );
                        if (ctx.isPointInStroke(x, y)) {
                            this.selShape = this.shapes[i].indexInBoard;
                            this.selShape = i;
                            this.xBefMov = x;
                            this.yBefMov = y;
                        }
                        break;
                    default:
                }
                ctx.closePath();
                if (ctx.isPointInPath(x, y)) {
                    this.selShape = this.shapes[i].indexInBoard;
                    this.selShape = i;
                    this.xBefMov = x;
                    this.yBefMov = y;
                    break;
                }
            }
        },
        /* function to perform the selected operation on the selected shape and send data to back-end
        then recieve the processed data and re-draw the new shape with the new creatures  */
        async performOrder(e) {
            this.movedX = e.offsetX - this.xBefMov;
            this.movedY = e.offsetY - this.yBefMov;
            console.log(this.movedY);
            var response = null;
            switch (this.oder) {
                case "COPY":
                    console.log(this.selShape);
                    response = await axios.post("http://localhost:8095/copy/", {
                        shapeIndex: this.selShape,
                        deltaX: this.movedX,
                        deltaY: this.movedY
                    });
                    this.shapes = response.data;
                    console.log(response.data);
                    this.clear();
                    if (this.shapes.length != 0) {
                        this.shapes.forEach(element => {
                            if (element != null) {
                                this.shapeStruct = element;
                                this.drawShapes();
                            }
                        });
                    }
                    this.selectedShape = false;
                    this.selShape = null;
                    this.currBoardIndex++;
                    this.oder = null;
                    break;
                case "MOVE":
                    response = await axios.post("http://localhost:8095/move/", {
                        shapeIndex: this.selShape,
                        deltaX: this.movedX,
                        deltaY: this.movedY
                    });
                    this.shapes = response.data;
                    console.log(response.data);
                    this.clear();
                    if (this.shapes.length != 0) {
                        this.shapes.forEach(element => {
                            if (element != null) {
                                this.shapeStruct = element;
                                this.drawShapes();
                            }
                        });
                    }
                    this.selectedShape = false;
                    this.selShape = null;
                    this.currBoardIndex++;
                    this.oder = null;
                    break;
                case "DELETE":
                    response = await axios.post(
                        "http://localhost:8095/delete/",
                        {
                            shapeIndex: this.selShape
                        }
                    );
                    this.shapes = response.data;
                    this.clear();
                    if (this.shapes.length != 0) {
                        this.shapes.forEach(element => {
                            if (element != null) {
                                this.shapeStruct = element;
                                this.drawShapes();
                            }
                        });
                    }
                    this.selectedShape = false;
                    this.selShape = null;
                    this.currBoardIndex++;
                    this.oder = null;
                    break;
                case "RESIZE":
                    console.log(this.selShape);
                    response = await axios.post(
                        "http://localhost:8095/resize/",
                        {
                            shapeIndex: this.selShape,
                            ratio: this.resizeRatio
                        }
                    );
                    this.shapes = response.data;
                    console.log(response.data);
                    this.clear();
                    if (this.shapes.length != 0) {
                        this.shapes.forEach(element => {
                            if (element != null) {
                                this.shapeStruct = element;
                                this.drawShapes();
                            }
                        });
                    }
                    this.selectedShape = false;
                    this.selShape = null;
                    this.currBoardIndex++;
                    this.oder = null;
                    break;
                default:
            }
            this.setcurrIndex();
        },
        /* function to save the data in file */
        async save(x) {
            var path = prompt("Enter save path followed by name like\n \"C:\\Users\\Dell\\Desktop\\test.json\"");
            await axios.post("http://localhost:8095/save/", {
                name: path,
                fileType: x
            });
            this.setcurrIndex();
        },
        /* function to load data from file */
        async load(x) {
            var path = prompt("Enter path of the file that want to bel loaded like\n \"C:\\Users\\Dell\\Desktop\\test.json\"");
            const response = await axios.post("http://localhost:8095/load/", {
                name: path,
                fileType: x
            });
            this.shapes = response.data;
            this.clear();
            if (this.shapes.length != 0) {
                this.shapes.forEach(element => {
                    if (element != null) {
                        this.shapeStruct = element;
                        this.drawShapes();
                    }
                });
            }
            this.selectedShape = false;
            this.setcurrIndex();
        },
        /* function to connect between bac and front-end to set the current board */ 
        async setcurrIndex() {
            const response = await axios.get("http://localhost:8095/index/set");
            this.currBoardIndex = response.data;
        },
        /* undo function */
        async undo() {
            const response = await axios.post("http://localhost:8095/undo/", {
                currBoardIndex: this.currBoardIndex,
                choice: "UNDO"
            });
            this.shapes = response.data;
            this.clear();
            this.shapes.forEach(element => {
                if (element != null) {
                    this.shapeStruct = element;
                    this.drawShapes();
                }
            });
            this.selectedShape = false;
            // if (this.currBoardIndex > 0) {
            //     this.currBoardIndex--;
            // }
            this.setcurrIndex();
        },
        /* redo function */
        async redo() {
            const response = await axios.post("http://localhost:8095/undo/", {
                currBoardIndex: this.currBoardIndex,
                choice: "REDO"
            });
            this.shapes = response.data;
            this.clear();
            this.shapes.forEach(element => {
                if (element != null) {
                    this.shapeStruct = element;
                    this.drawShapes();
                }
            });
            this.selectedShape = false;
            this.setcurrIndex();
        },
        /* functions to set the shape that will be drawn */
        setRectangle() {
            this.shapeStruct.points = [];
            this.shapeStruct.shapeType = "RECTANGLE";
            this.selectedShape = true;
        },
        setSquare() {
            this.shapeStruct.points = [];
            this.shapeStruct.shapeType = "SQUARE";
            this.selectedShape = true;
        },
        setCircle() {
            this.shapeStruct.points = [];
            this.shapeStruct.shapeType = "CIRCLE";
            this.selectedShape = true;
        },
        setLine() {
            this.shapeStruct.points = [];
            this.shapeStruct.shapeType = "LINE";
            this.selectedShape = true;
        },
        setEllipse() {
            this.shapeStruct.points = [];
            this.shapeStruct.shapeType = "ELLIPSE";
            this.selectedShape = true;
        },
        setTriangle() {
            this.shapeStruct.points = [];
            this.shapeStruct.shapeType = "TRIANGLE";
            this.selectedShape = true;
        },
        /* functions to set the operations that will be acted on the shape */
        setCopy() {
            this.oder = "COPY";
        },
        setMove() {
            this.oder = "MOVE";
        },
        setDelete() {
            this.oder = "DELETE";
        },
        setResize() {
            this.oder = "RESIZE";
            this.resizeRatio = prompt("Enter resize ratio please");
        },
        /* clear functions */
        clear() {
            var canvas = document.getElementById("myCanvas");
            var context = canvas.getContext("2d");
            context.clearRect(0, 0, canvas.width, canvas.height);
        },
        async btnclear() {
            this.clear();
            const ans = await axios.get("http://localhost:8095/clear/");
            this.currBoardIndex += ans.data;
        }
    }
};
</script>

<style>
.main {
    background-image: url("./images/cool-background.png");
    background-size: cover;
}
.shapes {
    text-align: center;
}
.square {
    height: 40px;
    width: 40px;
    background-image: url("./images/square.png");
    background-size: cover;
    border: none;
    margin: 2px;
    cursor: pointer;
}
.square:hover {
    background-image: url("./images/sq\ hov.png");
}
.rectangle {
    height: 40px;
    width: 40px;
    background-image: url("./images/rectangle.png");
    background-size: cover;
    border: none;
    margin: 2px;
    cursor: pointer;
}
.rectangle:hover {
    background-image: url("./images/rec\ hov.png");
}
.ellipse {
    height: 40px;
    width: 40px;
    background-image: url("./images/ellipse.png");
    background-size: cover;
    border: none;
    margin: 2px;
    cursor: pointer;
}
.ellipse:hover {
    background-image: url("./images/ell\ hov.png");
}
.triangle {
    height: 40px;
    width: 40px;
    background-image: url("./images/triangle.png");
    background-size: cover;
    border: none;
    margin: 2px;
    cursor: pointer;
}
.triangle:hover {
    background-image: url("./images/tri\ hov.png");
}
.circle {
    height: 40px;
    width: 40px;
    background-image: url("./images/circle.png");
    background-size: cover;
    border: none;
    margin: 2px;
    cursor: pointer;
}
.circle:hover {
    background-image: url("./images/circle\ ho.png");
}
.line {
    height: 40px;
    width: 40px;
    background-image: url("./images/line.png");
    background-size: cover;
    border: none;
    margin: 2px;
    cursor: pointer;
}
.line:hover {
    background-image: url("./images/line\ hov.png");
}
.move {
    height: 40px;
    width: 40px;
    background-image: url("./images/move.png");
    background-size: cover;
    border: none;
    margin: 2px;
    cursor: pointer;
}
.move:hover {
    background-image: url("./images/move\ hove.png");
}
.delete {
    height: 40px;
    width: 40px;
    background-image: url("./images/del.png");
    background-size: cover;
    border: none;
    margin: 2px;
    cursor: pointer;
}
.delete:hover {
    background-image: url("./images/del\ hov.png");
}
.resize {
    height: 40px;
    width: 40px;
    background-image: url("./images/resize.png");
    background-size: cover;
    border: none;
    margin: 2px;
    cursor: pointer;
}
.resize:hover {
    background-image: url("./images/resize\ hov.png");
}
.copy {
    height: 40px;
    width: 40px;
    background-image: url("./images/copy.png");
    background-size: cover;
    border: none;
    margin: 2px;
    cursor: pointer;
}
.copy:hover {
    background-image: url("./images/copy\ hov.png");
}
.opt {
    display: inline-block;
    padding: 0.35em 1.2em;
    border: 0.1em solid #000000;
    margin: 0 0.3em 0.3em 0;
    border-radius: 0.25cm;
    box-sizing: border-box;
    text-decoration: none;
    font-family: "Roboto", sans-serif;
    font-weight: 300;
    color: #000000;
    text-align: center;
    cursor: pointer;
}
.opt:hover {
    color: #ffffff;
    background-color: #000000;
}
@media all and (max-width: 30em) {
    .opt {
        display: block;
        margin: 0.4em auto;
    }
}
label {
    color: #ffffff;
    margin: 8px;
    margin-left: 20px;
}
.drawing-board {
    cursor: crosshair;
    background-color: rgb(255, 255, 255);
    position: relative;
    display: block;
    margin: auto;
    outline: rgb(82, 82, 82) 3px solid;
}
</style>
