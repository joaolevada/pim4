//selecionando todas as ancora do menu que contem os hrefs 
const menuItems = document.querySelectorAll('.menu a[href^="#"');
document.querySelector('html','body').animate({
    
})

// percorrendo cada elemento de ancora do menu items <a>
menuItems.forEach(item =>{
    //localizando evento de clique
    item.addEventListener('click',scrollToIdOnclick);
});


function scrollToIdOnclick(event){
    //prevendo o evento padrão
    event.preventDefault();
     // localizando o elemento que ele clicou
    console.log(event);

    const to = PegarScrool( event.target)-10;
    scrollPosition(to)
}

function PegarScrool(element){
    // localizando o id do elemento clicando
    const id = element.getAttribute('href');
    console.log(id);
    return to = document.querySelector(id).offsetTop; //retornando a posição do elemento clicando
}
//fazendo um scrool suave na pagina até a posição desejada
function scrollPosition(to){
    
    /*
    window.scroll({
        top:to,
        behavior:"smooth",
    });
    */
    smoothScrollTo(0,to,4500);
    
}




// Caso deseje suporte a browsers antigos / que não suportam scroll smooth nativo
/**
 * Smooth scroll animation
 * @param {int} endX: destination x coordinate
 * @param {int) endY: destination y coordinate
 * @param {int} duration: animation duration in ms
 */
function smoothScrollTo(endX, endY, duration) {
    const startX = window.scrollX || window.pageXOffset;
    const startY = window.scrollY || window.pageYOffset;
    const distanceX = endX - startX;
    const distanceY = endY - startY;
    const startTime = new Date().getTime();
  
    duration = typeof duration !== 'undefined' ? duration : 1200;
  
    // Easing function
    const easeInOutQuart = (time, from, distance, duration) => {
      if ((time /= duration / 2) < 1) return distance / 2 * time * time * time * time + from;
      return -distance / 2 * ((time -= 2) * time * time * time - 2) + from;
    };
  
    const timer = setInterval(() => {
      const time = new Date().getTime() - startTime;
      const newX = easeInOutQuart(time, startX, distanceX, duration);
      const newY = easeInOutQuart(time, startY, distanceY, duration);
      if (time >= duration) {
        clearInterval(timer);
      }
      window.scroll(newX, newY);
    }, 1000 / 60); // 60 fps
  };







/**
 * 
 * 
 */