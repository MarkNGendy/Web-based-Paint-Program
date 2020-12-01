<template>
    <div class="Paint">
        <h1>{{ msg }}</h1>
    </div>
    <div class="main">
        <div>
            <button class="opt" @click="undo">Undo</button>
            <button class="opt" @click="redo">Redo</button>
            <button class="opt">Save</button>
            <button class="opt">Load</button>
            <button class="opt" @click="clear">Clear</button>
        </div>
        <div class="shapes">
            <button class="square" @click="setSquare"></button>
            <button class="rectangle" @click="setRectangle"></button>
            <button class="circle" @click="setCircle"></button>
            <button class="triangle" @click="setTriangle"></button>
            <button class="ellipse" @click="setEllipse"></button>
            <button class="line" @click="setLine"></button>
            <button @click="drawShapes">draw</button>
        </div>
        <div>
            <button class="move"></button>
            <button class="delete"></button>
            <button class="resize"></button>
        </div>
        <label class="label">select the fill color:</label>
        <input type="color" id="myColor" />
        <label class="label">select the stroke color:</label>
        <input type="color" id="myStroke" />
        <label class="label">stroke width</label>
        <input type="number" id="strokeWidth" min="0" max="5" />
        <canvas
            id="myCanvas"
            width="1500"
            height="800"
            class="drawing-board"
            @click="setPoint"
            @mousewheel="drawShapes"
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
    data() {
        return {
            currBoardIndex: 0,
            shapes: [null],
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
            shape: null,
            selectedShape: false
        };
    },
    mounted() {
        var c = document.getElementById("myCanvas");
        this.canvas = c.getContext("2d");
    },
    methods: {
        async setPoint(e) {
            if (this.selectedShape == true) {
                console.log("seting point");
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
                        this.shape == "rectangle") ||
                    (this.shapeStruct.points.length == 2 &&
                        this.shape == "square") ||
                    (this.shapeStruct.points.length == 2 &&
                        this.shape == "line") ||
                    (this.shapeStruct.points.length == 3 &&
                        this.shape == "triangle") ||
                    (this.shapeStruct.points.length == 3 &&
                        this.shape == "ellipse")
                ) {
                    await this.sendRequest();
                } else if (
                    this.shapeStruct.points.length == 2 &&
                    this.shape == "circle"
                ) {
                    this.shapeStruct.points.push(this.shapeStruct.points[1]);
                    await this.sendRequest();
                }
            }
        },
        async drawShapes() {
            var canvas = document.getElementById("myCanvas");
            var color = document.getElementById("myColor");
            var stroke = document.getElementById("myStroke");
            var stWidth = document.getElementById("strokeWidth");
            if (canvas.getContext) {
                var ctx = canvas.getContext("2d");
                ctx.fillStyle = color.value;
                ctx.strokeStyle = stroke.value;
                switch (this.shape) {
                    case "rectangle":
                        ctx.fillRect(
                            this.shapeStruct.points[0].x,
                            this.shapeStruct.points[0].y,
                            this.shapeStruct.width,
                            this.shapeStruct.length
                        );
                        if (stWidth.value > 0) {
                            ctx.lineWidth = stWidth.value;
                            ctx.strokeRect(
                                this.shapeStruct.points[0].x,
                                this.shapeStruct.points[1].y,
                                this.shapeStruct.width,
                                this.shapeStruct.length
                            );
                        }
                        this.shapeStruct.shapeType = "RECTANGLE";
                        break;
                    case "square":
                        ctx.fillRect(
                            this.shapeStruct.points[0].x,
                            this.shapeStruct.points[0].y,
                            this.shapeStruct.sideLength,
                            this.shapeStruct.sideLength
                        );
                        if (stWidth.value > 0) {
                            ctx.lineWidth = stWidth.value;
                            ctx.strokeRect(
                                this.shapeStruct.points[0].x,
                                this.shapeStruct.points[0].y,
                                this.shapeStruct.sideLength,
                                this.shapeStruct.sideLength
                            );
                        }
                        this.shapeStruct.shapeType = "SQUARE";
                        break;
                    case "circle":
                        ctx.beginPath();
                        ctx.arc(
                            this.shapeStruct.points[0].x,
                            this.shapeStruct.points[0].y,
                            this.shapeStruct.radius,
                            0,
                            2 * Math.PI
                        );
                        ctx.fill();
                        if (stWidth.value > 0) {
                            ctx.lineWidth = stWidth.value;
                            ctx.stroke();
                        }
                        this.shapeStruct.shapeType = "CIRCLE";
                        break;
                    case "triangle":
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
                        if (stWidth.value > 0) {
                            ctx.lineWidth = stWidth.value;
                            ctx.stroke();
                        }
                        this.shapeStruct.shapeType = "TRIANGLE";
                        break;
                    case "ellipse":
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
                        if (stWidth.value > 0) {
                            ctx.lineWidth = stWidth.value;
                            ctx.stroke();
                        }
                        this.shapeStruct.shapeType = "ELLIPSE";
                        break;
                    case "line":
                        ctx.beginPath();
                        ctx.moveTo(
                            this.shapeStruct.points[0].x,
                            this.shapeStruct.points[0].y
                        );
                        ctx.lineWidth = stWidth.value > 0 ? stWidth.value : 1;
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
        async sendRequest() {
            const response = await axios.post("http://localhost:8095/shapes/", {
                shape: this.shapeStruct,
                operation: "CREATE"
            });
            this.shapes = response.data;
            this.clear;
            if (this.shapes.length != 0) {
                this.shapes.forEach(element => {
                    this.shapeStruct = element;
                    // console.log(this.shapeStruct);
                    this.drawShapes();
                });
            }
            this.selectedShape = false;
            this.currBoardIndex++;
            console.log(this.shapes);
        },
        async undo() {
            console.log();
            const response = await axios.post("http://localhost:8095/undo/", {
                currBoardIndex: this.currBoardIndex,
                choice: "UNDO"
            });
            this.shapes = response.data;
            this.clear;
            // this.shapes.forEach(element => {
            //     this.shapeStruct = element;
            //     this.drawShapes();
            // });
            // this.selectedShape = false;
            // if(this.currBoardIndex >= 0) {
            //     this.currBoardIndex--;
            // }
        },
        async redo() {
            const response = await axios.post("http://localhost:8095/undo/", {
                currBoardIndex: this.currBoardIndex,
                operation: "REDO"
            });
            this.shapes = response.data;
            // console.log(this.shapes);
            this.shapes.forEach(element => {
                this.shapeStruct = element;
                console.log(this.shapeStruct);
                this.drawShapes();
                this.selectedShape = false;
            });
            this.currBoardIndex++;
        },
        setRectangle() {
            this.shape = "rectangle";
            this.shapeStruct.points = [];
            this.shapeStruct.shapeType = "RECTANGLE";
            this.selectedShape = true;
        },
        setSquare() {
            this.shape = "square";
            this.shapeStruct.points = [];
            this.shapeStruct.shapeType = "SQUARE";
            this.selectedShape = true;
        },
        setCircle() {
            this.shape = "circle";
            this.shapeStruct.points = [];
            this.shapeStruct.shapeType = "CIRCLE";
            this.selectedShape = true;
        },
        setLine() {
            this.shape = "line";
            this.shapeStruct.points = [];
            this.shapeStruct.shapeType = "LINE";
            this.selectedShape = true;
        },
        setEllipse() {
            this.shape = "ellipse";
            this.shapeStruct.points = [];
            this.shapeStruct.shapeType = "ELLIPSE";
            this.selectedShape = true;
        },
        setTriangle() {
            this.shape = "triangle";
            this.shapeStruct.points = [];
            this.shapeStruct.shapeType = "TRIANGLE";
            this.selectedShape = true;
        },
        clear() {
            var canvas = document.getElementById("myCanvas");
            var context = canvas.getContext("2d");
            context.clearRect(0, 0, canvas.width, canvas.height);
            // this.shapeStruct.points = [];
        }
    }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
.main {
    background-image: url("./cool-background.png");
    background-size: cover;
}
.shapes {
    text-align: center;
}
.square {
    height: 40px;
    width: 40px;
    background-image: url("./square.png");
    background-size: cover;
    border: none;
    margin: 2px;
    cursor: pointer;
}
.square:hover {
    background-image: url("./sq\ hov.png");
}
.rectangle {
    height: 40px;
    width: 40px;
    background-image: url("./rectangle.png");
    background-size: cover;
    border: none;
    margin: 2px;
    cursor: pointer;
}
.rectangle:hover {
    background-image: url("./rec\ hov.png");
}
.ellipse {
    height: 40px;
    width: 40px;
    background-image: url("./ellipse.png");
    background-size: cover;
    border: none;
    margin: 2px;
    cursor: pointer;
}
.ellipse:hover {
    background-image: url("./ell\ hov.png");
}
.triangle {
    height: 40px;
    width: 40px;
    background-image: url("./triangle.png");
    background-size: cover;
    border: none;
    margin: 2px;
    cursor: pointer;
}
.triangle:hover {
    background-image: url("./tri\ hov.png");
}
.circle {
    height: 40px;
    width: 40px;
    background-image: url("./circle.png");
    background-size: cover;
    border: none;
    margin: 2px;
    cursor: pointer;
}
.circle:hover {
    background-image: url("./circle\ ho.png");
}
.line {
    height: 40px;
    width: 40px;
    background-image: url("./line.png");
    background-size: cover;
    border: none;
    margin: 2px;
    cursor: pointer;
}
.line:hover {
    background-image: url("./line\ hov.png");
}
.move {
    height: 40px;
    width: 40px;
    background-image: url("./move.png");
    background-size: cover;
    border: none;
    margin: 2px;
    cursor: pointer;
}
.move:hover {
    background-image: url("./move\ hove.png");
}
.delete {
    height: 40px;
    width: 40px;
    background-image: url("./del.png");
    background-size: cover;
    border: none;
    margin: 2px;
    cursor: pointer;
}
.delete:hover {
    background-image: url("./del\ hov.png");
}
.resize {
    height: 40px;
    width: 40px;
    background-image: url("./resize.png");
    background-size: cover;
    border: none;
    margin: 2px;
    cursor: pointer;
}
.resize:hover {
    background-image: url("./resize\ hov.png");
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
