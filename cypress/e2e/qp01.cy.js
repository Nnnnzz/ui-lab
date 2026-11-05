const makePizzaName = () => {
  const d = new Date();
  const two = (n) => String(n).padStart(2, '0');
  const day = d.getFullYear() + two(d.getMonth() + 1) + two(d.getDate());
  const time = two(d.getHours()) + two(d.getMinutes()) + two(d.getSeconds());
  return `Lab Pizza ${day}-${time}`;
};

describe('QP-01 — A custom pizza reaches the admin list', () => {
  it('keeps restrictions, blocks rating without login, and appears in admin', () => {
    const pizzaName = makePizzaName();

    cy.intercept('POST', '**/api/pizza').as('createPizza');
    cy.visit('/');
    cy.get('h1').should('contain.text', 'Looking to break out of your pizza routine?');

    cy.contains('label', 'Advanced').click();
    cy.contains('label', 'Max Calories per Slice').parent().find('input')
      .should('be.visible').clear().type('600').should('have.value', '600');
    cy.get('select').should('be.visible').find('option').contains(/^Knife$/).should('exist');
    cy.get('select').select('Knife');
    cy.get('#default-checkbox').check().should('be.checked');
    cy.get('input#pizza-name').clear().type(pizzaName).should('have.value', pizzaName);

    cy.get('[name="pizza-please"]').click();
    cy.wait('@createPizza').then(({ response }) => {
      expect(response, 'POST /api/pizza response').to.exist;
      expect(response.statusCode).to.be.within(200, 299);
      expect(response.body.pizza.name).to.equal(pizzaName);
      expect(response.body.pizza.tool).not.to.equal('Knife');
      expect(response.body.calories).to.be.at.most(600);
      cy.wrap(response.body).as('qp01Response');
      cy.log(`POST /api/pizza: ${JSON.stringify(response.body)}`);
    });

    cy.contains('p', /^Name:/).should('be.visible').and('contain.text', pizzaName);
    cy.contains('p', /^Tool:/).should('be.visible').invoke('text').then((text) => {
      const tool = text.replace('Tool:', '').trim();
      expect(tool).not.to.equal('Knife');
      cy.get('@qp01Response').its('pizza.tool').should('equal', tool);
    });
    cy.contains('p', /^Calories per slice:/).should('be.visible').invoke('text').then((text) => {
      const calories = Number(text.split(':').pop().trim());
      expect(calories).to.be.at.most(600);
      cy.get('@qp01Response').its('calories').should('equal', calories);
    });

    cy.get('@qp01Response').then((body) => {
      cy.writeFile('evidence/qp01-api-response.json', body);
      cy.writeFile('evidence/qp01-summary.json', {
        pizzaName,
        tool: body.pizza.tool,
        calories: body.calories,
        adminListVerified: false
      });
    });

    cy.get('[name="rate-5"]').click();
    cy.get('#rate-result').should('be.visible').and('have.text', 'Please log in first.');

    cy.visit('/admin');
    cy.get('#username').should('be.visible').and('have.value', 'admin');
    cy.get('#password').should('be.visible').and('have.value', 'admin');
    cy.get('button[type=submit]').click();

    // Verify the authenticated admin page by waiting for the created pizza itself.
    // The shared site may render the recommendations heading asynchronously,
    // so the test should assert the actual business result instead of a page heading.
    cy.location('pathname').should('eq', '/admin');
    cy.contains(pizzaName, { timeout: 30000 }).should('be.visible');
    cy.get('@qp01Response').then((body) => {
      cy.contains(pizzaName).closest('li').should('contain.text', body.pizza.tool);
      cy.writeFile('evidence/qp01-summary.json', {
        pizzaName,
        tool: body.pizza.tool,
        calories: body.calories,
        adminListVerified: true
      });
    });
    cy.screenshot('qp01-admin-success');
  });
});
