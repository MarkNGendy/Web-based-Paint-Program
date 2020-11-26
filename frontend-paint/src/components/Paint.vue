<template>
  <div class="Paint">
  <h1>{{msg}}</h1>
  </div>
  <div class="main">
    <div>
      <button class="opt">undo</button>
      <button class="opt">redo</button>
      <button class="opt">save</button>
      <button class="opt">load</button>
      <button class="opt">clear</button>
    </div>
    <div class= "shapes">
      <button class="square"></button>
      <button class="rectangle"></button>
      <button class="circle"></button>
      <button class="triangle"></button>
      <button class="ellipse"></button>
      <button class="line"></button>
    </div>
    <div>
      <button class="move"></button>
      <button class="delete"></button>
      <button class="resize"></button>
    </div>
    <label class="label">select the color:</label>
    <input type="color"> 
    <canvas class="drawing-board"></canvas>
  </div>
</template>

<script>

export default {
  name: "Paint",
  props: {
    msg: String
  }
};

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
.main{
background-image: url("./cool-background.png");
background-size: cover;
}
.shapes{
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
.square:hover{
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
.rectangle:hover{
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
.ellipse:hover{
  background-image: url("./ell\ hov.png");
  }
.triangle{
	height: 40px;
  width: 40px;
  background-image: url("./triangle.png");
  background-size: cover;
  border: none;
  margin: 2px;
  cursor: pointer;
}
.triangle:hover{
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
.circle:hover{
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
.line:hover{
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
.move:hover{
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
.delete:hover{
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
.resize:hover{
  background-image: url("./resize\ hov.png");
}
.opt{
  display:inline-block;
  padding:0.35em 1.2em;
  border:0.1em solid #000000;
  margin:0 0.3em 0.3em 0;
  border-radius: 0.25cm;
  box-sizing: border-box;
  text-decoration:none;
  font-family:'Roboto',sans-serif;
  font-weight:300;
  color:#000000;
  text-align:center;
  cursor: pointer;
  }
.opt:hover{
  color:#ffffff;
  background-color:#000000;
  }
@media all and (max-width:30em){
  .opt{
  display:block;
  margin:0.4em auto;
  }
}
label{
  color: #ffffff;
}
.drawing-board{
  cursor: crosshair;
  background-color: rgb(255, 255, 255);
  height: 80%;
  width: 80%;
  position: relative;
  display: block; 
  margin:auto;
  outline: rgb(82, 82, 82) 3px solid;
}
</style>
