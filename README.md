# WebAutomation

Test scenario: Verify checkout screen has item added to cart from item page
Steps:
- Go to amazon.com.au
- Perform login
- Clear cart
- Perform search with a key word
- Select random item from the list of items
- Add item to cart
- Proceed to checkout
- Find item in checkout and Assert
- Sign out

Framework:
- Page object model
- Util class has selenium methods
- Generic class has application methods
- App has the actual testcase
- Web element locators are passed from object.properties
- Data to test case is passed from data.properties
