module.exports = {
    "extends": "airbnb-base",
    "parser": "babel-eslint",
    "parserOptions": {
        "sourceType": "module",
        "allowImportExportEverywhere": false,
        "codeFrame": true
    },
    "rules": {
        "no-unused-vars": "off",
        "prefer-destructuring": "off",
<<<<<<< HEAD
        "linebreak-style": "off"
=======
        "padded-blocks":"off"
>>>>>>> bbcc32c0e52cbd1e4a8bf271c9d0782ce2393221
      
    },
    "globals": {
        "doc": true,
        "fetch": true,
        "window": true
    }
};