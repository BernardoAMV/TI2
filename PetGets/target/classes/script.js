// document.getElementById('meuForm').addEventListener('submit', function(e)); 
// e.preventDefault();

// const codigo = document.getElementById('codigo').value;
// const login = document.getElementById('login').value;
// const senha = document.getElementById('senha').value;
// const sexo = document.getElementById('sexo').value;

// const data = {
// 	codigo : codigo,
// 	login : login,
// 	senha : senha,
// 	sexo : sexo
// 	};
	
async function listaUsuarios(){
    
    var usuarios = await fetch('http://localhost:5554/usuario/list').then(data => data.json())

    console.log(typeof(usuarios.usuarios[0]))

    usuarios = usuarios.usuarios

    console.log(usuarios)

    

    const jsonContainer = document.getElementById('jsonContainer');


    jsonContainer.innerHTML = "";

    usuarios.forEach(element => {
    
        jsonContainer.innerHTML += '<pre>' +element.codigo + " - " + element.login + '</pre>';

    });
    
    
}


onload = () => {
    document.getElementById("listar").addEventListener("click", listaUsuarios);
    document.getElementById("inserir").addEventListener("click", insereUsuario);
    document.getElementById("deletar").addEventListener("click", deleteUsuario);
    document.getElementById("atualizar").addEventListener("click", atualizarUsuario);
}


function insereUsuario(){
    const strcodigo = document.getElementById('codigo').value;
    const strlogin = document.getElementById('login').value;
    const strsenha = document.getElementById('senha').value;
    const strsexo = document.getElementById('sexo').value;
    let novoUsuario ={
        codigo : strcodigo,
        login : strlogin,
        senha : strsenha,
        sexo : strsexo
    };
    const options = {
        method: 'POST',
        body : JSON.stringify(novoUsuario)
    };
    var usuarios = fetch('http://localhost:5554/usuario/insert', options)
        .then(response => response.json())
        .then(data => {
            console.log('Usuário inserido com sucesso:', data);
            // Você pode realizar outras ações aqui após a inserção bem-sucedida
        })
        .catch(error => {
            console.error('Erro ao inserir usuário:', error);
        });


}
function deleteUsuario(){
    let strcodigo = document.getElementById("id_consulta").value;

    fetch(`http://localhost:5554/usuario/delete?codigo=${strcodigo}`);

}

function atualizarUsuario(){
    const strcodigo = document.getElementById('codigo').value;
    const strlogin = document.getElementById('login').value;
    const strsenha = document.getElementById('senha').value;
    const strsexo = document.getElementById('sexo').value;
    let novoUsuario ={
        codigo : strcodigo,
        login : strlogin,
        senha : strsenha,
        sexo : strsexo
    };
    const options = {
        method: 'POST',
        body : JSON.stringify(novoUsuario)
    };
    var usuarios = fetch('http://localhost:5554/usuario/update', options)
        .then(response => response.json())
        .then(data => {
            console.log('Usuário atualizado com sucesso:', data);
            // Você pode realizar outras ações aqui após a inserção bem-sucedida
        })
        .catch(error => {
            console.error('Erro ao atualizar usuário:', error);
        });
}
