describe('Explicit retry-based waits', () => {
  it('waits for the asynchronous box to appear', () => {
    cy.visit('https://www.selenium.dev/selenium/web/dynamic.html');
    cy.get('#adder').click();
    cy.get('#box0').should('be.visible').and('have.class', 'redbox');
  });
});
