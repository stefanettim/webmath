<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>


<style>

DIV#threeId{
width: 400px;
height: 400px;
margin-top: 1px;
background-color: #ddd;
margin-left: auto; 
margin-right: 0;
border: 2px solid #888;
border-radius: 3px;
}


</style>


<DIV id="threeId" class="panel"></DIV>


<!--
<script src='https://cdnjs.cloudflare.com/ajax/libs/three.js/r79/three.min.js'></script>
BAD
<script src='https://unpkg.com/three@0.141.0/build/three.js'></script>
<script src='https://unpkg.com/three@0.125.0/build/three.js'></script>

https://discourse.threejs.org/t/three-geometry-will-be-removed-from-core-with-r125/22401?page=2

GOOD
<script src='https://unpkg.com/three@0.124.0/build/three.js'></script>
<script src='https://unpkg.com/three@0.79.0/build/three.js'></script>
-->

<script src='https://cdnjs.cloudflare.com/ajax/libs/three.js/r79/three.min.js'></script>
 
<!-- Import maps polyfill -->
<!-- Remove this when import maps will be widely supported -->
<script async=true src="https://unpkg.com/es-module-shims@1.3.6/dist/es-module-shims.js"></script>
    
<script type="importmap">
  {
    "imports": {
      "three": "https://unpkg.com/three@0.124.0/build/three.module.js"
    }
  }
</script>


<script type="module">

// view-source:https://threejs.org/examples/webgl_clipping_advanced.html

const mydiv=document.getElementById("threeId");
console.log("width:"+mydiv.clientWidth);
let myDivObjBgColor = window.getComputedStyle(mydiv).backgroundColor;
console.log("color:"+myDivObjBgColor);

  import * as THREE from 'three';
  import { OrbitControls } from 'https://unpkg.com/three@0.124.0/examples/jsm/controls/OrbitControls.js';
  import { FontLoader } from 'https://unpkg.com/three@0.134.0/examples/jsm/loaders/FontLoader.js';
  import { TextGeometry } from 'https://unpkg.com/three@0.134.0/examples/jsm/geometries/TextGeometry.js';     

		const scene = new THREE.Scene();
//		scene.background=new THREE.Color(myDivObjBgColor);
		scene.background=new THREE.Color(0,0,0);

		const camera = new THREE.PerspectiveCamera( 50, mydiv.clientWidth / mydiv.clientHeight, 0.1, 1000 );

		const renderer = new THREE.WebGLRenderer();
		renderer.setSize( mydiv.clientWidth, mydiv.clientHeight );
		renderer.shadowMap.enabled = true;
		renderer.antialias=true;

		mydiv.appendChild( renderer.domElement );

// plane
const planSize=120;
const planeGeometry = new THREE.PlaneGeometry( planSize, planSize, 1, 1 );
planeGeometry.translate(0,0,-8);
const planTexture=new THREE.TextureLoader().load('${ctx}/img/wood.jpg');
planTexture.wrapS = THREE.RepeatWrapping;
planTexture.wrapT = THREE.RepeatWrapping;
planTexture.repeat.set( 5, 5 );
const planMaterial = new THREE.MeshPhongMaterial({ map: planTexture, shininess: 40, side: THREE.DoubleSide });
const ground = new THREE.Mesh( planeGeometry,planMaterial);
ground.receiveShadow = true;
scene.add( ground );


// materials
const clipMaterial = new THREE.MeshPhongMaterial( {
					color: 0x00ff10,
					shininess: 100,
					side: THREE.DoubleSide,
					clippingPlanes: ground,
					clipShadows: true,
					transparent: true,
					opacity: 0.6
				} );

const solidMat = new THREE.MeshPhongMaterial({color: 0x0000FF}); 


// small cylinder
const s1g = new THREE.CylinderGeometry( 1, 1, 1, 3, 1 );
const s1m = new THREE.Mesh( s1g, solidMat );
s1m.rotation.y = +1 * Math.PI / 3;
s1m.position.z = 10 - 0.5;
s1m.position.x = Math.sqrt(3)/2;
//scene.add( s1m );

// big cylinder
const radius=10;
let bigg = new THREE.CylinderGeometry( radius, radius, 1, 3, 1 );
const zoff=0;
bigg.translate(0,0,zoff);
//const bigm = new THREE.Mesh( bigg, clipMaterial );
//bigm.castShadow = true;
//scene.add( bigm );

//s1m.updateMatrix();
//bigg.merge(s1m.geometry, s1m.matrix, 1);

var mesh = new THREE.Mesh(bigg, clipMaterial);
mesh.castShadow = true;
//scene.add(mesh);

// edges
mesh.updateMatrix();
const edges = new THREE.EdgesGeometry( mesh.geometry );
const line = new THREE.LineSegments( edges, new THREE.LineBasicMaterial( { color: 0x00FF00 } ) );
//scene.add( line );

// center
const cg = new THREE.SphereGeometry( 0.6, 12, 12 );
const cm = new THREE.Mesh( cg, solidMat );
cm.castShadow = true;
scene.add( cm );

const group = new THREE.Group();
group.add( mesh );
group.add( s1m );
group.add( cm );
group.add( line );
scene.add( group );


// labels
const textMaterial = new THREE.MeshBasicMaterial( {color: 0xFF0000} );

const loader = new FontLoader();
var textGeometry1;
loader.load( 'https://unpkg.com/three@0.77.0/examples/fonts/helvetiker_bold.typeface.json', function ( font ) {

	textGeometry1 = new TextGeometry( '1', {
		font: font,
		size: 1,
		height: 0.1,
		curveSegments: 12,
		bevelEnabled: true,
		bevelThickness: 0.01,
		bevelSize: 0.01,
		bevelOffset: 0,
		bevelSegments: 5
	} );
	const textMesh1 = new THREE.Mesh( textGeometry1, textMaterial );
	textMesh1.castShadow = true;
    textMesh1.setRotationFromEuler(new THREE.Euler(Math.PI/2,Math.PI,0));
    textMesh1.position.set(radius*1.1*Math.cos((0/3+0.25)*2*Math.PI)+0.5,0,radius*1.1*Math.sin((0/3+0.25)*2*Math.PI)+zoff-0.5);
	scene.add( textMesh1 );

	const textGeometry2 = new TextGeometry( '2', {
		font: font,
		size: 1,
		height: 0.1,
		curveSegments: 12,
		bevelEnabled: true,
		bevelThickness: 0.01,
		bevelSize: 0.01,
		bevelOffset: 0,
		bevelSegments: 5
	} );
	const textMesh2 = new THREE.Mesh( textGeometry2, textMaterial );
	textMesh2.castShadow = true;
    textMesh2.setRotationFromEuler(new THREE.Euler(Math.PI/2,Math.PI,0));
    textMesh2.position.set(radius*1.1*Math.cos((2/3+0.25)*2*Math.PI)+0.5,0,radius*1.1*Math.sin((2/3+0.25)*2*Math.PI)+zoff);
	scene.add( textMesh2 );

	const textGeometry3 = new TextGeometry( '3', {
		font: font,
		size: 1,
		height: 0.1,
		curveSegments: 12,
		bevelEnabled: true,
		bevelThickness: 0.01,
		bevelSize: 0.01,
		bevelOffset: 0,
		bevelSegments: 5
	} );
	const textMesh3 = new THREE.Mesh( textGeometry3, textMaterial );
	textMesh3.castShadow = true;
    textMesh3.setRotationFromEuler(new THREE.Euler(Math.PI/2,Math.PI,0));
    textMesh3.position.set(radius*1.1*Math.cos((1/3+0.25)*2*Math.PI)+0.5,0,radius*1.1*Math.sin((1/3+0.25)*2*Math.PI)+zoff);
	scene.add( textMesh3 );

} );




// axes
const o = new THREE.Vector3(-20,-10,0);
const al=3;
const arrowX = new THREE.ArrowHelper( new THREE.Vector3( 1, 0, 0 ), o, al, 0xFF0000, 1, 0.5);
scene.add( arrowX );
const arrowY = new THREE.ArrowHelper( new THREE.Vector3( 0, 1, 0 ), o, al, 0x00FF00, 1, 0.5);
scene.add( arrowY );
const arrowZ = new THREE.ArrowHelper( new THREE.Vector3( 0, 0, 1 ), o, al, 0x0000FF, 1, 0.5);
scene.add( arrowZ );



// Lights

				scene.add( new THREE.AmbientLight( 0xffffff, 0.3 ) );

				const spotLight = new THREE.SpotLight( 0xffffff, 0.5 );
				spotLight.angle = Math.PI / 6;
				spotLight.penumbra = 0.2;
				spotLight.position.set( 13, 13, 16 );
				spotLight.castShadow = true;
				scene.add( spotLight );

				const dirLight = new THREE.DirectionalLight( 0xffffff, 0.5 );
				dirLight.position.set( 0, 30, 30 );
				//dirLight.castShadow = true;
				scene.add( dirLight );

const controls = new OrbitControls( camera, renderer.domElement );
controls.update();

// camera
camera.up.set(0, 0, 1);
//camera.lookAt(0, 0, 30 );
camera.position.set(7.39,20.54,11.47);
camera.setRotationFromEuler(new THREE.Euler(-1.15,0.35,2.99));

		function animate() {

group.rotation.y += Math.PI / 100;

			requestAnimationFrame( animate );
			renderer.render( scene, camera );

//console.log("position: "+camera.position.x.toFixed(2)+","+camera.position.y.toFixed(2)+","+camera.position.z.toFixed(2));
//console.log("rotation: "+camera.rotation.x.toFixed(2)+","+camera.rotation.y.toFixed(2)+","+camera.rotation.z.toFixed(2));

		};

		animate();
		
</script>
