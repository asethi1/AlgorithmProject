<!doctype html>
<!-- saved from url=(0044)http://kenedict.com/networks/worldcup14/vis/ , thanks Andre!-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF8">
  <title>Network | Static smooth curves - World Cup Network</title>

  <script type="text/javascript" src="js/vis.js"></script>
  <link type="text/css" rel="stylesheet" href="css/vis.css">

  <script src="js/WorldCup2014.js"></script>

  <style type="text/css">
    #mynetwork {
      width: 600px;
      height: 600px;
      border: 1px solid lightgray;
    }
  </style>
 
</head>

<body>

<h2> Calculating Diameter of the graph </h2>

<a href="newhtml.html" > Go Back</a>

<form name="form" id="myform" method="POST" action="/search">
    <h3> Enter start vertex and number of times you want to run BFS on the graph. <br>
        By default we are running BFS twice to get the diameter of the graph.</h3>
    Enter the start vertex number(Mandatory)   <br>  <input type="number" name="startVertex"  ><br>
    Number of times BFS to be run(Optional)<br><input type="text" name="bfsNoOfTimes" ><br>
    <input type="submit" value="submit">
    
</form>
<div id = "formOutput">
  <%
    if(request.getAttribute("path")!=null) {
  %>
    <%=request.getAttribute("path")%>
  <%}%>
</div>
<div style="width:700px; font-size:14px;">
  After submitting we are running BFS from the given start vertex and calculating the diameter of the graph and comparing it with log Base2 of the number of vertices.
  The complete graph is very computationally intensive to draw, Hence we have drawn only a subset of the whole dataset. here we have drawn around 1000 nodes and 10000 edges.
  <br/><br/>
  <br/><br/>
</div>

<!--<div id="mynetwork"></div>

<script type="text/javascript">
  var network;


  function redrawAll() {
    // remove positoins
    for (var i = 0; i < nodes.length; i++) {
      delete nodes[i].x;
      delete nodes[i].y;
    }

    // create a network
    var container = document.getElementById('mynetwork');
    var data = {
      nodes: nodes,
      edges: edges
    };
    var options = {
      nodes: {
        shape: 'dot',
        scaling: {
          min: 3,
          max: 10
        },
        font: {
          size: 12,
          face: 'Tahoma'
        }
      },
      edges: {
        width: 0.15,
        color: {inherit: 'from'},
        smooth: {
          type: 'continuous'
        }
      },
      physics: {
        stabilization: false,
        barnesHut: {
          gravitationalConstant: -80000,
          springConstant: 0.001,
          springLength: 200
        }
      },
      interaction: {
        tooltipDelay: 200,
        hideEdgesOnDrag: true
      }
    };

    // Note: data is coming from ./datasources/WorldCup2014.js
    network = new vis.Network(container, data, options);
  }

  redrawAll()
</script>-->
</body>
</html>
