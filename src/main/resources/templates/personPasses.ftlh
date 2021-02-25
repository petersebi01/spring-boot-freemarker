<#macro personDetails loadPersonPasses loadPerson loadLines>
    <div>
        <h2>Bérletek listája</h2>
        <div>
            <#list loadPersonPasses as pass>
                <#list loadLines as line>
                <a href="/buspass/people/${loadPerson.name}/${line.lineFrom}/${line.lineTo}/${pass.serialNumber}"}>
                    <li class="resourceList">
                        <table>
                            <thead>
                            <tr>
                                <th>Mettöl</th>
                                <th>Meddig</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>${pass.startDate}</td>
                                <td>${pass.endDate}</td>
                            </tr>
                            </tbody>
                        </table>
                    </li>
                </a>
                </#list>
            </#list>
        </div>
    </div>
</#macro>