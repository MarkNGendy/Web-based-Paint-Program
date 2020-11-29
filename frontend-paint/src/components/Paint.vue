<template>
    <div class="Paint">
        <h1>{{ msg }}</h1>
    </div>
    <div class="main">
        <div>
            <button class="opt">Undo</button>
            <button class="opt">Redo</button>
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
            <button class="freeDraw" @click="startFreeDraw"></button>
        </div>
        <div>
            <button class="move"></button>
            <button class="delete"></button>
            <button class="resize"></button>
        </div>
        <label class="label">select the fill color:</label>
        <input type="color" id="myColor">
        <label class="label">select the stroke color:</label>
        <input type="color" id="myStroke">
        <label class="label">stroke width</label>
        <input type="number" id="strokeWidth" min="0" max="5">
        <canvas
            id="myCanvas"
            width="1500"
            height="800"
            class="drawing-board"
            @mousedown="beginDrawing"
            @mouseup="stopDrawing"
            @mousemove="draw"
            
        ></canvas>
    </div>
</template>

<script>
// import axios from "axios";
export default {
    name: "Paint",
    props: {
        msg: String
    },
    data() {
        return {
            shapes: [null],
            shapeStruct: {
                ShapeType:'null',
                indexInBoard:'-1',
                points: [null],
                colour: 'null'
            },
            numOfShapes: 0,
            points: null,
            canvas: null,
            x: 0,
            y: 0,
            isDrawing: false,
            free: false,
            shape: null
        };
    },
    mounted(){
        var c = document.getElementById("myCanvas");
        this.canvas = c.getContext('2d');
    },
    methods: {
        showCoordinates(e) {
            this.x = e.offsetX;
            this.y = e.offsetY;
        },
        startFreeDraw(){
            this.free=true;
        },
        drawLine(x1, y1, x2, y2) {
            let ctx = this.canvas;
            var stroke = document.getElementById("myStroke");
            var stWidth = document.getElementById("strokeWidth");
            ctx.beginPath();
            ctx.lineWidth = stWidth.value!=0?stWidth.value:1 ;
            ctx.moveTo(x1, y1);
            ctx.lineTo(x2, y2);
            ctx.strokeStyle = stroke.value;
            ctx.stroke();
            ctx.closePath();
        },
        draw(e) {
            if(this.isDrawing) {
            this.drawLine(this.x, this.y, e.offsetX, e.offsetY);
            this.x = e.offsetX;
            this.y = e.offsetY;
            }
        },
        beginDrawing(e) {
            this.x = e.offsetX;
            this.y = e.offsetY;
            if(this.free){
                this.isDrawing = true;
                }
        },
        stopDrawing(e) {
            if (this.isDrawing && this.free) {
                this.drawLine(this.x, this.y, e.offsetX, e.offsetY);
                this.x = 0;
                this.y = 0;
                this.isDrawing = false;
            }
            else{
                var x2= e.offsetX;
                var y2= e.offsetY;
                var canvas = document.getElementById("myCanvas");
                var color = document.getElementById("myColor");
                var stroke = document.getElementById("myStroke");
                var stWidth = document.getElementById("strokeWidth");
                if(canvas.getContext){
                    var ctx = canvas.getContext("2d");
                    ctx.fillStyle = color.value;
                    ctx.strokeStyle = stroke.value;
                    var bigger = Math.abs(x2 - this.x) > Math.abs(y2 - this.y) ? x2 - this.x : y2 - this.y;
                    switch(this.shape){
                        case 'rectangle':
                            ctx.fillRect(this.x,this.y,x2-this.x,y2-this.y);
                            if(stWidth.value>0){
                                ctx.lineWidth = stWidth.value;
                                ctx.strokeRect(this.x,this.y,x2-this.x,y2-this.y);
                            }
                            break;
                        case 'square':
                            ctx.fillRect(this.x,this.y,bigger,bigger);
                            if(stWidth.value>0){
                                ctx.lineWidth = stWidth.value;
                                ctx.strokeRect(this.x,this.y,bigger,bigger);
                            }
                            break;
                        case 'circle':
                            ctx.beginPath();
                            ctx.arc(this.x, this.y, bigger, 0, 2 * Math.PI);
                            ctx.fill();
                            if(stWidth.value>0){
                                ctx.lineWidth = stWidth.value;
                                ctx.stroke();
                            }
                            break;
                        case 'triangle':
                            ctx.beginPath();
                            ctx.moveTo(this.x,this.y);
                            ctx.lineTo(x2,this.y);
                            ctx.lineTo((this.x+x2)/2, y2);
                            ctx.lineTo(this.x,this.y);
                            ctx.fill();
                            if(stWidth.value>0){
                                ctx.lineWidth = stWidth.value;
                                ctx.stroke();
                            }
                            break;
                        case 'ellipse':
                            ctx.beginPath();
                            ctx.ellipse(this.x, this.y, bigger, bigger/2, 0, 0, 2 * Math.PI);
                            ctx.fill();
                            if(stWidth.value>0){
                                ctx.lineWidth = stWidth.value;
                                ctx.stroke();
                            }
                            break;
                        case 'line':
                            ctx.beginPath();
                            ctx.moveTo(this.x, this.y);
                            ctx.lineTo(x2, y2);
                            ctx.stroke();
                            break;
                        default:
                    }
                }
            }
        },
        setRectangle(){
            this.shape='rectangle';
            this.free = false;
        },
        setSquare(){
            this.shape= 'square'
            this.free= false;
        },
        setCircle(){
            this.shape= 'circle'
            this.free= false;
        },
        setLine(){
            this.shape= 'line'
            this.free= false;
        },
        setEllipse(){
            this.shape= 'ellipse'
            this.free= false;
        },
        setTriangle(){
            this.shape= 'triangle'
            this.free= false;
        },
        clear(){
            var canvas = document.getElementById("myCanvas");
            var context = canvas.getContext('2d');
            context.clearRect(0, 0, canvas.width, canvas.height);
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
.freeDraw {
    height: 40px;
    width: 40px;
    background-image: url("./free.png");
    background-size: cover;
    border: none;
    margin: 2px;
    cursor: pointer;
}
.freeDraw:hover {
    background-image: url("./free\ hov.png");
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
