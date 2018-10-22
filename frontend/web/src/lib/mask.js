/* ========================================================================
 * Pure Mask JS: puremask.js v0.3.1
 * http://romulobrasil.com
 * Copyright (c) 2014 Rômulo Brasil
 * ========================================================================
 */

/* eslint-disable */

const PureMask = (function () {
    return {
      format(a, b) {
        function f(a) {
          let e; let f; let g; let h; let b = ''; const
            d = c.value; for (g = 0, h = 1; h && g < a.length; ++g) switch (e = d.charAt(g), f = a.charAt(g), a.charAt(g)) { case '#': /\d/.test(e) ? b += e : h = 0; break; case 'A': /[a-z]/i.test(e) ? b += e : h = 0; break; case 'N': /[a-z0-9]/i.test(e) ? b += e : h = 0; break; case 'X': b += e; break; default: b += f; }c.value = b;
        } var c = document.querySelector(a); let
          d = ''; if (void 0 === c || c === null) return console.log(`PureMask.js: class não foi declarada corretamente: ${a}.`), null; void 0 === c.dataset.mask || c.dataset.mask === null ? console.log(`PureMask.js: data-mask não foi declarada na Tag com a class: ${c.className}.`) : (d = c.dataset.mask, c.maxLength = d.length), b === !0 && (c.placeholder = d); c.value.length; c.addEventListener('keypress', (a) => { a.keyCode === 8 && a.keyCode === 46 || f(d); });
      },
    };
  }());
  
  export default PureMask;
  