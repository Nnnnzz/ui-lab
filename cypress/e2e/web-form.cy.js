describe('Selenium practice web form', () => {
  it('fills the form and checks the received page', () => {
    cy.visit('https://www.selenium.dev/selenium/web/web-form.html');
    cy.title().should('eq', 'Web form');
    cy.get('#my-text-id').type('Hello Selenium').should('have.value', 'Hello Selenium');
    cy.get('[name="my-select"]').select('Two').should('have.value', '2');
    cy.get('[name="my-password"]').type('Secret-26');
    cy.get('[name="my-textarea"]').type('Two lines');
    cy.get('#my-check-2').check().should('be.checked');
    cy.get('#my-radio-2').check().should('be.checked');
    cy.get('button[type="submit"]').click();
    cy.title().should('eq', 'Web form - target page');
    cy.get('#message').should('have.text', 'Received!');
  });
});
