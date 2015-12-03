<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Minified Small World Experiment </title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <link href="http://fonts.googleapis.com/css?family=Droid+Sans:400,700|Droid+Serif" rel="stylesheet" type="text/css">

    <link rel="stylesheet" href="css/reset.css">
    <!-- CSS reset -->
    <link rel="stylesheet" href="css/style.css">
    <!-- Gem style -->
    <link rel="stylesheet" href="css/product.css">
    <!-- Gem style -->
    <script src="js/modernizr.js"></script>
    <!-- Modernizr -->
    <link rel="stylesheet" href="css/animate.min.css">

    <script src="http://codepen.io/assets/libs/fullpage/jquery.js"></script>
    <script src="js/index.js"></script>
    <script src="js/carousel.js"></script>

    <script type="text/javascript">
        window.onload = function () {
            var chart = new CanvasJS.Chart("chartContainer",
                    {
                        title: {
                            text: "Main Vs Other SCC's"
                        },
                        data: [
                            {
                                type: "pie",
                                dataPoints: [
                                    {y: 97.5, indexLabel: "Main SCC - 97.5%"},
                                    {y: 2.5, indexLabel: "Other SCC's - 2.5 %"}
                                ]

                            }
                        ]
                    });

            chart.render();
        }
    </script>

    <script type="text/javascript" src="js/canvasjs.min.js"></script>

    <!--scrolling animation-->
    <script src="js/wow.min.js"></script>
    <script>
        new WOW().init();
    </script>


</head>
<body>
<div>

    <nav role="navigation" class="navbar navbar-default navbar-fixed-top"
         style="background-color: black; margin-bottom: 0px;">
        <div class="container-fluid">

            <div class="navbar-header">
                <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="#" class="navbar-brand"
                   style="font-size: 30px; color: white; font-stretch: wider; font-weight: 600;">Minified Small World
                    Experiment</a>
            </div>

            <div id="navbarCollapse" class="collapse navbar-collapse">

                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#Home" style="color: white;">Home</a></li>
                    <li><a href="#calc-diameter">Project Demo</a></li>
                    <li><a href="https://github.com/asethi1/AlgorithmProject">Github</a></li>
                    <li><a href="#WhoWeAre">Who We Are</a></li>
                    <!--<li><a href="#Contact_us">Contact Us</a></li>-->
                </ul>
            </div>
        </div>
    </nav>

    <div>
        <a id="Home"></a>

        <div class="bs-example">
            <div id="myCarousel" class="carousel slide" data-interval="2000" data-ride="carousel">
                <!-- Carousel items -->
                <div class="carousel-inner">
                    <div class="item active">
                        <img class="img-responsive center-block" src="Images/graph.PNG">
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="wow bounceInUp" style="visibility: visible; animation-name: bounceInUp;">
                <a id="What_We_Offer"></a>

                <div class="row" id="WhatWeOffer">
                    <div class="container">
                        <div class="wow slideInLeft" style="visibility: hidden; animation-name: none;">
                            <div class="row">
                                <div class="wow pulse animated" data-wow-delay="300ms" data-wow-duration="2s"
                                     style="visibility: hidden; animation-duration: 2s; animation-delay: 300ms; animation-iteration-count: infinite; animation-name: none;">
                                    <div class="product_head">Project Overview</div>
                                </div>
                                <div class="col-md-4 col-sm-6 products">
                                    <h2>
                                        <a><span class="glyphicon glyphicon-stats"></span></a>&nbsp;&nbsp;
                                        Experiment
                                    </h2>

                                    <p>We analyze the social network of Facebook user connections through a graph
                                        structure. We take a sparsely connected cluster of users and focus on the
                                        connections between users to find the degree of separation.
                                        <br>
                                        <br>

                                        <br>
                                        <br>
                                    </p>
                                </div>
                                <div class="col-md-4 col-sm-6 products ">
                                    <h2>
                                        <a><span class="glyphicon glyphicon-stats"></span></a>&nbsp;&nbsp;
                                        Dataset
                                    </h2>

                                    <p>We have taken Facebook’s anonymized data set from
                                        http://socialnetworks.mpi-sws.org/data-wosn2009.html with a size of 10.4MB. Each
                                        line contains two anonymized user identifiers, meaning the second user appeared
                                        in the first user's friend list. The graph contains 65,000 nodes representing
                                        users and 1,600,000 undirected edges (with no weights) representing the
                                        connections between users.
                                        <br>
                                        <br>

                                        <br>
                                        <br>
                                    </p>
                                </div>
                                <div class="col-md-4 col-sm-6 products " style="animation-name: slideInLeft ">
                                    <h2>
                                        <a><span class="glyphicon glyphicon-road"></span></a>&nbsp;&nbsp;Results</h2>

                                    <p>
                                        The interesting finding of our experiment is that the diameter of the graph
                                        comes out to be close to the logarithm of the number of vertices in the graph.
                                        This actually is the true degree of separation between two users in the graph
                                        representing our data set. The experiment is useful in determining how closely
                                        people are connected to each other.
                                    </p>
                                </div>
                            </div>

                            <div class="row">
                                <div class="wow pulse animated" data-wow-delay="300ms" data-wow-duration="2s"
                                     style="visibility: hidden; animation-duration: 2s; animation-delay: 300ms; animation-iteration-count: infinite; animation-name: none;">
                                    <div class="product_head">Strongly Connected Components (SCCs)</div>
                                </div>
                                <br><br>

                                <div class="products">
                                    <p>
                                        In our analysis of the graph, we found that we have a total of 144 SCCs. Out of
                                        144, one SCC dominated the others in terms of size. The size difference between
                                        the SCCs has been depicted in the following pie chart.
                                    </p></div>
                                <br><br>

                                <div align="middle">
                                    <div id="chartContainer" style="height: 300px; width: 35%; border: solid;"></div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="wow pulse animated" data-wow-delay="300ms" data-wow-duration="2s"
                                     style="visibility: hidden; animation-duration: 2s; animation-delay: 300ms; animation-iteration-count: infinite; animation-name: none;">
                                    <div class="product_head">Degree Distribution</div>
                                </div>
                                <br><br>

                                <div class="products">
                                    <p>
                                        The line graph below displays degree distribution of the most prominent SCC of
                                        our graph representing our experimental dataset. The X-axis represents the
                                        in-degree and the Y-axis represents the number of nodes in consideration.
                                    </p></div>
                                <br><br>

                                <div align="middle">
                                    <img src="Images/indegreeVSNodes.png" width="850" height="400" align="middle">
                                </div>
                            </div>


                            <div id="calc-diameter" class="row">
                                <div class="wow pulse animated" data-wow-delay="300ms" data-wow-duration="2s"
                                     style="visibility: hidden; animation-duration: 2s; animation-delay: 300ms; animation-iteration-count: infinite; animation-name: none;">
                                    <div class="product_head">Calculate diameter</div>
                                </div>
                                <br><br>

                                <div class="products">
                                    <form name="form" id="myform" method="POST" action="/search">
                                        <p>Enter the number id of the node from where you want to start calculating the
                                            diameter (required) :
                                            <input type="number" name="startVertex"><br>
                                        </p>

                                        <p>Enter the number of times you want to run BFS on the graph (optional) * :
                                            <input type="text" name="bfsNoOfTimes"><br></p>

                                        <p><input class="btn btn-large btn-primary" type="submit" value="Calculate"></p>

                                        <p>*By default we are running BFS twice to get the diameter of the graph.</p>
                                    </form>
                                </div>
                                <br><br>

                                <div id="formOutput" class="products">

                                    <%
                                        if (request.getAttribute("path") != null) {

                                    %>
                                    <p>The diameter of the graph obtained from the node input by the user is : <%=request.getAttribute("path")%></p>


                                    <div class="products" style="width:700px; font-size:14px;" onload="scrollToElement($('formOutput'))">
                                        <p>After calculating the diameter of the graph, we check its accuracy by
                                            comparing it with a more appropriate measure of degree of separation, i.e.
                                            the log base 2 for number of vertices:</p>

                                        <p>Diameter obtained : <%=request.getAttribute("path")%>
                                        <%--<p>Path obtained : <%=request.getAttribute("list")%>--%>
                                        </p>

                                        <p>Log base 2 for number of vertices : 15.872675</p>

                                        <p>As we can see, the diameter obtained is less than (or equal to) the log
                                            value, hence our approximation of the diameter is acceptably accurate for
                                            this given dataset.</p>
                                        <br/><br/>
                                        <br/><br/>
                                    </div>


                                    <%}%>
                                </div>
                                <br><br>

                            </div>

                            <br>


                        </div>
                    </div>
                </div>
            </div>
        </div>


        <!--<a name="WhoWeAre"></a>-->
        <a id="WhoWeAre"></a>
        <section id="cd-team" class="cd-section whoweare">
            <div class="cd-container">
                <div class="wow pulse animated" data-wow-delay="300ms" data-wow-duration="2s"
                     style="visibility: hidden; animation-duration: 2s; animation-delay: 300ms; animation-iteration-count: infinite; animation-name: none;">
                    <div class="product_head" style="color: #000000">Who We Are</div>
                </div>
                <br><br>
                <ul>
                    <li>
                        <figure>
                            <img src="Images/romina.jpg" alt="Team member 1" height="330">
                        </figure>

                        <div class="cd-member-info">
                            Romina Nayak
                            <span>Email: romina.nayak@rutgers.edu</span>
                            <span>RUID: 163001899</span>
                        </div>
                        <!-- cd-member-info -->
                    </li>
                    <li>
                        <figure>
                            <img src="Images/yang.jpg" alt="Team member 2" height="330">
                        </figure>

                        <div class="cd-member-info">
                            Yu Yang
                            <span>Email: yangyu.9415@rutgers.edu</span>
                            <span>RUID: 163005962</span>
                        </div>
                        <!-- cd-member-info -->
                    </li>
                    <li>
                        <figure>
                            <img src="Images/amit.jpg" alt="Team member 3" height="330">
                        </figure>

                        <div class="cd-member-info">
                            Amit Sethi
                            <span>Email: as2378@scarletmail.rutgers.edu</span>
                            <span>RUID: 168002564</span>
                        </div>
                        <!-- cd-member-info -->

                    </li>
                </ul>
            </div>
            <!-- cd-container -->
        </section>
        <!-- cd-team -->

        <div class="cd-overlay"></div>
        </main>
    </div>

</div>


<div class="p_footer">
    <div class="col-sm-12 t_right">
        <a href="http://cs.rutgers.edu/"></a>
        Minified Small World it is..

    </div>
    <br><br>
    .

</div>
</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>

<script language="JavaScript">
    function scrollToElement(ele) {
        $(window).scrollTop(ele.offset().top).scrollLeft(ele.offset().left);
    }
</script>

</body>
</html>
