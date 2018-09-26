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
        "prefer-destructuring": "off"
      
    },
    "globals": {
        "doc": true,
        "fetch": true,
        "window": true
    }
};