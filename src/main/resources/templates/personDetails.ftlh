<#--<#ftl auto_esc=false>-->
<html>
    <head>
        <title>Személy adatok</title>
<#--        <link rel="stylesheet" href="../static/style.css">-->
    </head>
    <body>
        <#assign link="/buspass/lines/${lineFrom}/${lineTo}/${personData.name}">
        <div id="root">
            <div>
                <div id="oldal_menu" class="menu">
                    <button class="close" onClick={this.closeNav}>&times;</button>
                    <a href="/buspass/lines">Lines</a>
                    <#if personData??>
                        <#if personData.name??>
                            <a href="/buspass/people/${personData.name}/lines">My lines</a>
                            <a href="/buspass/people/${personData.name}/passes">My passes</a>
                        </#if>
                    </#if>
                    <br/>
                </div>
                <div class="header">
                    <span style="font-size: 26px; cursor: pointer" onClick="this.openNav()">&#9776; Menü</span>
                    <div class="header-center">
                    </div>
                </div>
                <div class="card">
                    <div>
                        <h1>${personData.name} adatai</h1>
                        <p class="details">Név: ${personData.name}</p>
                        <br/>
                        <p class="details">Munkahely: ${personData.firm!}</p>
                        <br/>
                        <a href="/buspass/people/${personData.name}/passes">
                            <p>Bérletek megtekintése</p>
                        </a>
                        <a href="/buspass/people/${personData.name}/lines">
                            <p>Járatok megtekintése</p>
                        </a>
                    </div>
                </div>

                <div class="form-content">
                    <form method="POST" action="/buspass/lines/${lineFrom}/${lineTo}/${personData.name}" <#-- onSubmit={this.updateEmployee}--> >
                        <h3>Felhasználó szerkesztése</h3>
                        <div class="container">
                            <label>Név:</label>
                            <input type="text" name="name" value=${personData.name} required/>
                            <br/>
                            <label>ID:</label>
                            <input type="text" name="id" value=${personData.id} required/>
                            <br/>
                            <label>Munkahely</label>
                            <input type="text" name="firm" value=${personData.firm!}/>
                            <br/>
                            <input class="signupbtn" type="submit" value="Mentés"/>
                        </div>
                    </form>
                </div>

                <h1>Bérlet készítése</h1>
                <form action="/buspass/people/${personData.name}/${lineFrom}/${lineTo}" method="post">
                    <div class="container">
                        <label>Mettől:</label>
                        <input type="text" id="startDate" name="startDate" required/>
                        <br/>
                        <label>Meddig:</label>
                        <input type="text" id="endDate" name="endDate" required/>
                        <br/>
                        <label>Sablon:</label>
                        <input type="number" id="passTemplate" name="passTemplate" value="1" required/>
                        <br/>
                        <input class="signupbtn" type="submit" value="Mentés"/>
                    </div>
                </form>
            </div>
        </div>
        <script>
            openNav = () => {
                document.getElementById("oldal_menu").style.width = "250px";
                document.getElementById("main").style.marginLeft = "250px";
            }

            closeNav = () => {
                document.getElementById("oldal_menu").style.width = "0";
                document.getElementById("main").style.marginLeft= "0";
            }
        </script>
    </body>
</html>
<#--</#macro>-->