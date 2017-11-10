Please implement a service that reads in the provided xml and json files and exposes the information via a RESTful API.

File structure:
- products.xml contains master data for all products.
- prices.json contains price information for all products

The API should be designed in a way, that it allows the following use cases:
1. List all products with their master data (name, description, sku)
2. Show one product with master data and prices (name, description, sku, prices)
3. Find current product price by unit (piece, package)

Out of scope:
- Persistence of data
- Authentication/Authorization