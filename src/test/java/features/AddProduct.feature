
@tag
Feature: Purchase products from Amazon
  I want to use this template for my feature file

Background: 
Given User landed on Amazon Home Page

@tag1
  Scenario Outline: Purchase Laptop 
    Given User searched product <productName>
    When User selects product sequence <productSequence> from searched product list
    And User added the selected product to cart
    Then User verified the product price in the cart

    Examples: 
      | productName  	| productSequence |
      | Laptop 				|	1								|
      

@tag2
  Scenario Outline: Purchase Monitor
    Given User searched product <productName>
    When User selects product sequence <productSequence> from searched product list
    And User added the selected product to cart
    Then User verified the product price in the cart

    Examples: 
      | productName  	| productSequence |
      | Monitor 			|	2								|


@tag3
  Scenario Outline: Purchase two products
    Given User searched product <firstProduct>
    When User selects product sequence <firstProductSequence> from searched product list
    And User added the selected product to cart
    Given User searched product <secondProduct>
    When User selects product sequence <secondProductSequence> from searched product list
    And User added the selected product to cart
    Then User verified the product price in the cart

    Examples: 
      | firstProduct  	| firstProductSequence 	| secondProduct  	| secondProductSequence 	|
      | Headset 				|	1											| Keyboard 				|	1												|      

    

