<#macro loginDatas loadLine>
<#assign username="">
    <body>
    <script type="text/javascript">
        function handleSubmit(){

            let name = document.getElementById("username");
            let password = document.getElementById("password");
            console.log(document.getElementById('username'));
            console.log(document.getElementById('password'));

            let form = {
                name: name
            }
            console.log(JSON.stringify(form))
            fetch('/buspass/people', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(form)
            }).then(response => {
                response.json().then((data) => {
                    //console.log(data);
                    localStorage.setItem('userId', data.userID);
                    localStorage.setItem('token', data.token);
                    localStorage.setItem('username', data.name);
                });
            }).then(() => {
                console.log(localStorage.getItem('userId'));
                console.log(localStorage.getItem('username'));
                console.log(localStorage.getItem('permission'));
            });

        }
    </script>
        <h3>Kérlek jelentkezz be</h3>
        <form action="/buspass/people/${loadLine.lineFrom}/${loadLine.lineTo}" method="post">
            <label>
                <input type="text" id="name" name="name" placeholder="Név">
            </label>
            <br>
            <br>
            <label>
                <input type="password" id="id" name="id" placeholder="Jelszó">
            </label>
            <br>
            <br>
            <br>
            <input type="submit" id="save" value="Bejelentkezés">
        </form>
    </body>
</#macro>