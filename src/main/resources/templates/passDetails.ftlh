<!DOCTYPE html>
<html lang="English">
        <head>
<#--            <link rel="stylesheet" href="css/style.css">-->
            <title>Projekt</title>
        </head>
    <body>
        <#assign link="/buspass/people/${personData.name}/${lineFrom}/${lineTo}/${passData.serialNumber}/">
        <div id="root">
            <div>
                <div id="oldal_menu" class="menu">
                    <button class="close" onClick={this.closeNav}>&times;</button>
                    <a href="/buspass/lines">Lines</a>
                    <#if personData??>
                        <#if personData.name??>
                            <a href="/buspass/lines/${lineFrom}/${lineTo}/${personData.name}">Me</a>
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
                        <h1>${passData.serialNumber} számú bérlet adatai</h1>
                        <p class="details">Sorszám: ${passData.serialNumber}</p>
                        <br/>
                        <p class="details">Mettől: ${passData.startDate}</p>
                        <br/>
                        <p class="details">Meddig: ${passData.endDate}</p>
                        <br/>
                        <p class="details">Ár: ${passData.price}</p>
                        <br/>
                    </div>
                </div>
                <div class="form-content">
                    <form action="/buspass/people/${personData.name}/${lineFrom}/${lineTo}/${passData.serialNumber}" method="post">
                        <h3>Bérlet szerkesztése</h3>
                        <div class="container">
                            <label>Sorszám:</label>
                            <input type="text" id="serialNumber" name="serialNumber" value=${passData.serialNumber} readonly/>
                            <br/>
                            <label>Mettől:</label>
                            <input type="text" id="startDate" name="startDate" value=${passData.startDate} required/>
                            <br/>
                            <label>Meddig:</label>
                            <input type="text" id="endDate" name="endDate" value=${passData.endDate} required/>
                            <br/>
                            <label>Ár:</label>
                            <input type="text" id="price" name="price" value=${passData.price} readonly/>
                            <br/>
                            <label>Sablon módosítása:</label>
                            <input type="number" id="passTemplate" name="passTemplate" value=${passData.passTemplate} required/>
                            <br/>
                            <input class="signupbtn" type="submit" value="Mentés"/>
                        </div>
                    </form>
                    </br>
                    </br>
                    </br>
                    <div>
                        <#if passData.passTemplate == 1>
                            <#assign sablon1=r"<table><tbody><tr><td>Foglaló K.F.T.</td><tr><td></td><td></td></tr><tr><td>Bérlet</td></tr><tr>
                            <td>Szériaszám:</td><td>${passData.serialNumber}</td></tr><tr><td>Név</td><td>${personData.name}</td></tr><tr>
                            <td>Útvonal honnan:</td><td>${lineFrom}</td></tr><tr><td>Útvonal hova:</td><td>${lineTo}</td></tr><tr>
                            <td>Munkahely által fizetett:</td><td>${personData.firm!}</td></tr><tr><td>Érvényes:</td><td>${passData.startDate} - ${passData.endDate}</td>
                            </tr><tr><td>Ár</td><td>${passData.price}</td></tr></tbody></table>">

<#--                         A változás az előző kódhoz képest, hogy egy "r" karakter került a string elé, amely jelzi a Freemarkernek hogy rendereléskor teljes mértékben raw-ként tekintse
                             a szöveget, és ne hejettesítse be elsőként az adatokat, hanem csak az "interpret" végrehajtása után.-->
                            <#assign data1=sablon1?interpret>
                            <@data1 />

                        <#elseif passData.passTemplate == 2>
                            <#assign sablon2=r"<div class='card'><h4>Foglaló K.F.T.</h4></p><h2>Bérlet</h2><p>Sorszám: ${passData.serialNumber}</p><div class='container'>
                            <h4>Név: <p><b>${personData.name}</b></p></h4><p><b>Honnan: </b>${lineFrom}</p><p><b>Hova: </b>${lineTo}</p>
                            <p>Munkahely által fizetett: ${personData.firm!}</p>
                            </div><h4>Érvényes: <b>${passData.startDate} - ${passData.endDate}</b></h4><div class='container'><h3>Ár: ${passData.price}</div>
                            </div>">

<#--                        r a string elött, hogy raw adatról van szó    -->
                            <#assign data2=sablon2?interpret>
                            <@data2 />
                        </#if>
                    </div>
                </div>
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