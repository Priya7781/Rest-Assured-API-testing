import { test, expect } from '@playwright/test';

// test('has title', async ({ page }) => {
//   await page.goto('https://playwright.dev/');

//   // Expect a title "to contain" a substring.
//   await expect(page).toHaveTitle(/Playwright/);
// });

test('simple get request', async ({ request }) => {
  const response = await request.get('https://conduit-api.bondaracademy.com/api/tags');
  const responseBody = await response.json();

  console.log(responseBody);

  await expect(responseBody.tags).toContain('Test');
  await expect(response).toBeOK();
});


test('simple post request', async ({ request }) => {
  const response = await request.post('https://conduit-api.bondaracademy.com/api/articles/', {  
    headers: {
      authorization: 'Token eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjo1MTQwMH0sImlhdCI6MTc4MjA0Nzg5MiwiZXhwIjoxNzg3MjMxODkyfQ.5yI7nmsgeimrcqw8wJb5WFAIeTd4MDTQjjE0npAiiIk'
    },
    data: {
      article: {title: "AQCX TEST", description: "jhghjghj", body: "jhfjhfjf", tagList: []}
    }
   });

  const responseBody = await response.json();

  console.log(responseBody);

  });
  // Expects page to have a heading with the name of Installation.
 
