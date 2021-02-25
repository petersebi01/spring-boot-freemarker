<#assign link="/buspass/lines">
<#macro lineList loadedLines>
    <div>
        <h2>Beosztások listája</h2>
        <div>
            <#list loadedLines as line>
                <a href="/buspass/lines/${line.lineFrom}/${line.lineTo}"}>
                    <li class="resourceList">
                        <table>
                            <thead>
                            <tr>
                                <th>Honnan</th>
                                <th>Hova</th>
                            </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>${line.lineFrom}</td>
                                    <td>${line.lineTo}</td>
                                </tr>
                            </tbody>
                        </table>
                    </li>
                </a>
            </#list>
        </div>
    <#--    <div>-->
    <#--        <button type="button" onClick={this.openRegistrationForm}>Új beosztás létrehozása</button>-->
    <#--        {this.state.registerFormView ?-->
    <#--        <div class="form-content">-->
    <#--            <h2>Új beosztás létrehozása</h2>-->
    <#--            <form onSubmit={this.handleRegistration}>-->
    <#--                <div class="container">-->
    <#--                    <label>Beosztás neve:</label>-->
    <#--                    <input type="text" name="name" required/>-->
    <#--                    <br/>-->
    <#--                    <label>Kezdés dátuma:</label>-->
    <#--                    <input type="date" name="lineFrom" required/>-->
    <#--                    <br/>-->
    <#--                    <label>Befejezés dátuma:</label>-->
    <#--                    <input type="date" name="lineTo" required/>-->
    <#--                    <br/>-->
    <#--                    <label>Feladat:</label>-->
    <#--                    <input class="signupbtn" type="submit" value="Regisztrálás"/>-->
    <#--                </div>-->
    <#--            </form>-->
    <#--        </div>-->
    <#--        : ""}-->
    <#--    </div>-->
    </div>
</#macro>