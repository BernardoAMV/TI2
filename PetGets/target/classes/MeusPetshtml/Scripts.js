window.onload(listaAnimais());
    async function listaAnimais(){
    var responsavel = getUser();
    var animais = await fetch(`http://localhost:5554/animal/listMyPets?responsavel=${responsavel}`).then(data => data.json())
  
  console.log(animais);
    animais = animais.animais
    
    const jsonContainer = document.getElementById('jsonContainer');
  
    jsonContainer.innerHTML = "";
    animais.forEach(element => {
    
        jsonContainer.innerHTML +=  `<div class="card" id="${element.idAnimal}">
        <div class="card-image">
          <img src=${element.fotoAnimal} alt="Imagem do Card">
        </div>
        <div class="card-content">
          <div id="nomec1"><h3>${element.nomeAnimal}</h3></div>
          <div class="flex">
            <ul>
              <div>
                <p>Raca:${element.racaAnimal} </p>
                <p>Idade:${element.idadeAnimal} anos</p>
                <p>Sexo: ${element.sexoDoAnimal}</p>
                <p>Especie: ${element.especieAnimal}</p>
                <p>Descricao: ${element.descricaoAnimal}</p>
                <p>Responsavel: ${element.nomeResponsavel}</p>
                <p>Contato:${element.contatoResponsavel}</p>
              </div>
            <button onclick="deleteAnimal(${element.idAnimal})">Excluir</button>
            </ul>
            
          </div>
        </div>
      </div>`
    });
  }
  function getUser(){

    var user = JSON.parse(localStorage.getItem('dadosUser')).cpf;
    
    return user;
    }

function atualizarUsuario(){
    var nomeInstituicao = document.getElementById("nomeInstituicao").value;
    var cnpj = document.getElementById("cnpj").value;
    var senha = document.getElementById("senha").value;
    var contato = document.getElementById("contato").value;
    var dados = {
        nomeInstituicao: nomeInstituicao,
        cnpj: cnpj,
        senha: senha,
        contato: contato
      };
      const options = {
          method: 'POST',
          body : JSON.stringify(dados)
      };
      var usuarios = fetch('http://localhost:5554/animal/update', options)
          .then(response => response.json())
          .then(data => {
              console.log('Usuário atualizado com sucesso:', data);
              
          })
          .catch(error => {
              console.error('Erro ao atualizar usuário:', error);
          });
          document.getElementById("nomeInstituicao").value ="";
          document.getElementById("cnpj").value = "";
          document.getElementById("senha").value = "";
          document.getElementById("contato").value = "";
          document.getElementById("cnpj").value = "";
  }
  function deleteAnimal(id){
    console.log(id)
    fetch(`http://localhost:5554/animal/delete?id=${id}`)
    .then(response => response.json())
    .then(data => {
            console.log('Usuário deletado com sucesso');
            
        })
        .catch(error => {
            console.error('Erro ao atualizar usuário:', error);
        });

}