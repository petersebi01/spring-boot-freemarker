<!DOCTYPE html>
<html>
<#import "login.ftlh" as loginFile>
<#--<#macro lineDetails loadLine loadPerson>-->
<#--    <#assign link="/buspass/lines/${line.lineFrom}/${line.lineTo}">-->
    <head>
<#--        <link rel="stylesheet" href="css/style.css">-->
        <title>Részletek</title>
    </head>
    <body>
        <div class="card">
            <div>
                <h1>A ${lineData.lineFrom} -> ${lineData.lineTo} járat adatai</h1>
                <p class="details">Indulás: ${lineData.lineFrom}</p>
                <p class="details">Érkezés: ${lineData.lineTo}</p>
                <br/>
                <p class="details">Egy hónapos bérlet ára: ${lineData.monthly}</p>
                <br/>
                <p class="details">Három hetes bérlet ára: ${lineData.threeWeekly}</p>
                <br/>
                <p class="details">Két hetes bérlet ára: ${lineData.twoWeekly}</p>
                <br/>
                <#if personData??>
                    <#if personData.name??>
                        <a href="/buspass/people/${personData.name}/${lineData.lineFrom}/${lineData.lineTo}/passes">
                            <p>Bérletek megtekintése</p>
                        </a>
                    </#if>
                <#else>
                    <@loginFile.loginDatas loadLine=lineData/>
                </#if>
            </div>
        </div>
    </body>
<#--</#macro>-->
</html>