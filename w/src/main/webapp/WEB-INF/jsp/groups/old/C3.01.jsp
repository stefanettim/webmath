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
border: 2px solid #888;
border-radius: 3px;
}


</style>

<div id="panel">

	<div id="controls">
			<input id="checkboxRotateId" type="checkbox" /> 
	</div>

	<DIV id="threeId" class="panel"></DIV>

</div>



 
<!-- Import maps polyfill -->
<!-- Remove this when import maps will be widely supported -->
<script async=true src="https://unpkg.com/es-module-shims@1.3.6/dist/es-module-shims.js"></script>
    
<script type="importmap">
  {
    "imports": {
      "three": "https://unpkg.com/three/build/three.module.js"
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
  import { OrbitControls } from 'https://unpkg.com/three/examples/jsm/controls/OrbitControls.js';
  import { FontLoader } from 'https://unpkg.com/three/examples/jsm/loaders/FontLoader.js';
  import { TextGeometry } from 'https://unpkg.com/three/examples/jsm/geometries/TextGeometry.js';  
   
		const scene = new THREE.Scene();
//		scene.background=new THREE.Color(myDivObjBgColor);
		scene.background=new THREE.Color(0,0,0);

		const camera = new THREE.PerspectiveCamera( 50, mydiv.clientWidth / mydiv.clientHeight, 0.1, 1000 );

		const renderer = new THREE.WebGLRenderer();
		renderer.setSize( mydiv.clientWidth, mydiv.clientHeight );
		renderer.shadowMap.enabled = true;
		renderer.antialias=true;

		mydiv.appendChild( renderer.domElement );

const group = new THREE.Group();

// plane
const planSize=120;
const planeGeometry = new THREE.PlaneGeometry( planSize, planSize, 1, 1 );
planeGeometry.translate(0,0,-12);
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

const gelMaterial = new THREE.MeshPhongMaterial( {
					color: 0x0000ff,
					shininess: 80,
					side: THREE.DoubleSide,
					clippingPlanes: ground,
					clipShadows: true,
					transparent: true,
					opacity: 0.5
				} );

const solidMat = new THREE.MeshPhongMaterial({color: 0x0000FF}); 
const edgeMat = new THREE.LineBasicMaterial( { color: 0x00FF00 } ); 


// small cylinder
const s1g = new THREE.CylinderGeometry( 1, 1, 1, 3, 1 );
const s1m = new THREE.Mesh( s1g, clipMaterial );
s1m.castShadow = true;
const s2m = s1m.clone();
const s3m = s1m.clone();


const s1gr = new THREE.Group();
s1gr.add( s1m );
s1m.updateMatrix();
const s1e = new THREE.EdgesGeometry( s1m.geometry );
const s1l = new THREE.LineSegments( s1e, edgeMat );
s1gr.add( s1l );
s1gr.rotation.y = +1 * Math.PI / 3;
s1gr.position.z = 10 - 0.5;
s1gr.position.x = Math.sqrt(3)/2;
group.add( s1gr );


const s2gr = new THREE.Group();
s2gr.add( s2m );
s2m.updateMatrix();
const s2e = new THREE.EdgesGeometry( s2m.geometry );
const s2l = new THREE.LineSegments( s2e, edgeMat );
s2gr.add( s2l );
s2gr.rotation.y = Math.PI;
s2gr.position.z = -5 - 0.5;
s2gr.position.x = 10 * Math.sqrt(3)/2 - Math.sqrt(3)/2;
group.add( s2gr );


const s3gr = new THREE.Group();
s3gr.add( s3m );
s3m.updateMatrix();
const s3e = new THREE.EdgesGeometry( s3m.geometry );
const s3l = new THREE.LineSegments( s3e, edgeMat );
s3gr.add( s3l );
s3gr.rotation.y = Math.PI;
s3gr.position.z = -5 + 1;
s3gr.position.x = -10 * Math.sqrt(3)/2;
group.add( s3gr);



// big cylinder
const radius=10;
let bigg = new THREE.CylinderGeometry( radius, radius, 1, 3, 1 );
const zoff=0;
bigg.translate(0,0,zoff);
const bigm = new THREE.Mesh( bigg, clipMaterial );
bigm.castShadow = true;
group.add( bigm );
// edges
const bige = new THREE.EdgesGeometry( bigg );
const bigl = new THREE.LineSegments( bige, edgeMat );
group.add( bigl );


// center
const cg = new THREE.SphereGeometry( 0.5, 12, 12 );
const cm = new THREE.Mesh( cg, solidMat );
cm.castShadow = true;
group.add( cm );

// axis
const ag = new THREE.CylinderGeometry( 0.2, 0.2, 5, 12, 1 );
const am = new THREE.Mesh( ag, solidMat );
am.castShadow = true;
group.add( am );


// labels
const textMaterial = new THREE.MeshBasicMaterial( {color: 0xFF0000} );

const loader = new FontLoader();
var textGeometry1;

var textMesh1, textMesh2, textMesh3;

loader.load( 'https://unpkg.com/three@0.77.0/examples/fonts/helvetiker_bold.typeface.json', function ( font ) {

    var rscale = 1.12;


	textGeometry1 = new TextGeometry( '1', {
		font: font,
		size: 1,
		height: 0.2,
		curveSegments: 12,
		bevelEnabled: true,
		bevelThickness: 0.01,
		bevelSize: 0.01,
		bevelOffset: 0,
		bevelSegments: 5
	} );
	textMesh1 = new THREE.Mesh( textGeometry1, textMaterial );
	textMesh1.castShadow = true;
    textMesh1.setRotationFromEuler(new THREE.Euler(Math.PI/2,Math.PI,0));
    textMesh1.position.set(radius*rscale*Math.cos((0/3+0.25)*2*Math.PI),0,radius*rscale*Math.sin((0/3+0.25)*2*Math.PI)+zoff);
	group.add( textMesh1 );

/*
	textMesh1.position.set(0.5,0,-0.5);
	const textSphere1 = new THREE.SphereGeometry( 1, 12, 12 );
	const textSphere1m = new THREE.Mesh( textSphere1, gelMaterial );
	textSphere1m.castShadow = true;
	textSphere1m.add( textMesh1 );
    textSphere1m.position.set(radius*rscale*Math.cos((0/3+0.25)*2*Math.PI),0,radius*rscale*Math.sin((0/3+0.25)*2*Math.PI)+zoff);
 	group.add( textSphere1m );
*/

	const textGeometry2 = new TextGeometry( '2', {
		font: font,
		size: 1,
		height: 0.2,
		curveSegments: 12,
		bevelEnabled: true,
		bevelThickness: 0.01,
		bevelSize: 0.01,
		bevelOffset: 0,
		bevelSegments: 5
	} );
	textMesh2 = new THREE.Mesh( textGeometry2, textMaterial );
	textMesh2.castShadow = true;
    textMesh2.setRotationFromEuler(new THREE.Euler(Math.PI/2,Math.PI,0));
    textMesh2.position.set(radius*rscale*Math.cos((2/3+0.25)*2*Math.PI),0,radius*rscale*Math.sin((2/3+0.25)*2*Math.PI)+zoff);
	group.add( textMesh2 );

	const textGeometry3 = new TextGeometry( '3', {
		font: font,
		size: 1,
		height: 0.2,
		curveSegments: 12,
		bevelEnabled: true,
		bevelThickness: 0.01,
		bevelSize: 0.01,
		bevelOffset: 0,
		bevelSegments: 5
	} );
	textMesh3 = new THREE.Mesh( textGeometry3, textMaterial );
	textMesh3.castShadow = true;
    textMesh3.setRotationFromEuler(new THREE.Euler(Math.PI/2,Math.PI,0));
    textMesh3.position.set(radius*rscale*Math.cos((1/3+0.25)*2*Math.PI),0,radius*rscale*Math.sin((1/3+0.25)*2*Math.PI)+zoff);
	group.add( textMesh3 );

} );

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



// axes
const o = new THREE.Vector3(-5,5,-10);
const al=2;
const arrowX = new THREE.ArrowHelper( new THREE.Vector3( 1, 0, 0 ), o, al, 0xFF0000, 1, al/3);
scene.add( arrowX );
const arrowY = new THREE.ArrowHelper( new THREE.Vector3( 0, 1, 0 ), o, al, 0x00FF00, 1, al/3);
scene.add( arrowY );
const arrowZ = new THREE.ArrowHelper( new THREE.Vector3( 0, 0, 1 ), o, al, 0x0000FF, 1, al/3);
scene.add( arrowZ );




const controls = new OrbitControls( camera, renderer.domElement );
controls.update();

// camera
camera.up.set(0, 0, 1);

//camera.position.set(20,20,20);
camera.position.set(7.28,22.93,15.54);
camera.setRotationFromEuler(new THREE.Euler(-1.00,0.25,2.98));

scene.add( group );


const cb = document.querySelector('#checkboxRotateId');

	function animate() {

        if(cb.checked){
			group.rotation.y += Math.PI / 50;

			textMesh1.up.set(0, 1, 1);
			textMesh1.lookAt(new THREE.Vector3( 0, 100, 0 ));
			textMesh2.up.set(0, 1, 1);
			textMesh2.lookAt(new THREE.Vector3( 0, 100, 0 ));
			textMesh3.up.set(0, 1, 1);
			textMesh3.lookAt(new THREE.Vector3( 0, 100, 0 ));

		}

		requestAnimationFrame( animate );
		renderer.render( scene, camera );

		console.log("position: "+camera.position.x.toFixed(2)+","+camera.position.y.toFixed(2)+","+camera.position.z.toFixed(2));
		console.log("rotation: "+camera.rotation.x.toFixed(2)+","+camera.rotation.y.toFixed(2)+","+camera.rotation.z.toFixed(2));

	};

	animate();
		
</script>
