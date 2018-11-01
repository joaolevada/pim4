export const environment = {

  production: true,

  backend: {
    protocol: 'http',
    host: 'localhost',
    port: 3200,
    api: 'api',
    url: `${this.protocol}://${this.host}:${this.port}/${this.api}`
  },

};
