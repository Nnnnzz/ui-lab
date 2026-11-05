const { defineConfig } = require('cypress');

module.exports = defineConfig({
  video: true,
  screenshotOnRunFailure: true,
  defaultCommandTimeout: 30000,
  screenshotsFolder: 'screenshots',
  videosFolder: 'videos',
  e2e: {
    baseUrl: 'https://quickpizza.grafana.com',
    specPattern: 'e2e/**/*.cy.js',
    supportFile: false,
    video: true,
    setupNodeEvents(on, config) {
      return config;
    }
  }
});
