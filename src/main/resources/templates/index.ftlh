<!DOCTYPE html>
<#import "lines.ftlh" as lineFile>
<#import "personLines.ftlh" as personLinesFile>
<#import "personPasses.ftlh" as personPassesFile>
<html lang="English">
    <head>
<#--        <link rel="stylesheet" href="css/style.css">-->
        <title>Projekt</title>
    </head>
    <body>
        <div id="root">
            <div>
                <div id="oldal_menu" class="menu">
                    <button class="close" onClick={this.closeNav}>&times;</button>
                    <a href="/buspass/lines">Lines</a>
                    <#if personData??>
                        <#if personData.name??>
                            <#list lines_person as line>
                                <#list line.people as person>
                                    <#if person.name == personData.name>
                                        <#if person.id == personData.id>
                                            <#assign currentLine = line>
                                            <#break >
                                        </#if>
                                    </#if>
                                </#list>
                            </#list>
                            <a href="/buspass/lines/${currentLine.lineFrom}/${currentLine.lineTo}/${personData.name}">Me</a>
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

                <div id="main">
                    <div id="container">
                        <div id="tart">
                            <div class="font">

                                <h1>Jegy vásárlás</h1>
                                    <p>${springMacroRequestContext.getRequestUri()}</p>
                                    <#assign link1 = "/buspass/lines">
                                    <#if link1 == springMacroRequestContext.getRequestUri()>
                                        <@lineFile.lineList loadedLines=lines/>
                                    <#else>
                                        <#if personData??>
                                            <#if personData.name??>
                                                <#assign link2 = "/buspass/people/${personData.name}/lines">
                                                <#assign link3 = "/buspass/people/${personData.name}/passes">
                                                <#if link2 == springMacroRequestContext.getRequestUri()>
    <#--                                                <@personLinesFile.personDetails loadPersonLines=peronLines/>-->
                                                    <#if lineList??>
                                                        <@lineFile.lineList loadedLines=lines_person/>
                                                    </#if>
                                                <#elseif link3 == springMacroRequestContext.getRequestUri()>
                                                    <@personPassesFile.personDetails loadPersonPasses=personPasses loadPerson=personData loadLines=lines_person/>
                                                </#if>
                                            </#if>
                                        <#else>
                                            <#include "login.ftlh">
                                        </#if>
                                    </#if>
                            </div>
                        </div>
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