const subTitle = document.getElementById("titulo");

setTimeout(() => {

    subTitle.innerHTML = 'A gente se importa com sua empresa';

    typeWriter(subTitle);

}, 1300);
function typeWriter(element) {

    const text = element.textContent.split(''); // o split ira montar um array separando as letras

    element.innerHTML = '';

    text.forEach((letra, i) => {

        setTimeout(() => {

            element.innerHTML += letra;

        }, 75 * i); // 75 ms para cada indice no nosso array (letra)

    });
    console.log(text);

}