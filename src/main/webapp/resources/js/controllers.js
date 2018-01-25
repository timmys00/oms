var cartApp = angular.module('cartApp', []); 
 
      cartApp.controller('cartCtrl', function($scope, $http) { 
 
          $scope.refreshCart = function(cartId) { 
              $http.get('/WebPresent/rest/cart/' + $scope.cartId) 
                  .success(function(data) { 
                      $scope.cart = data; 
                  }); 
          }; 
 
          $scope.clearCart = function() { 
              $http.delete('/WebPresent/rest/cart/' + $scope.cartId) 
                  .success(function(data) { 
                      $scope.refreshCart($scope.cartId); 
                  }); 
 
          }; 
 
          $scope.initCartId = function(cartId) { 
              $scope.cartId = cartId; 
              $scope.refreshCart($scope.cartId); 
          }; 
 
          $scope.addToCart = function(productId) { 
              $http.put('/WebPresent/rest/cart/add/' + productId) 
                  .success(function(data) { 
                      alert("Product Successfully added to the Cart!"); 
                  }); 
          }; 
          $scope.removeFromCart = function(productId) { 
              $http.put('/WebPresent/rest/cart/remove/' + productId) 
                  .success(function(data) { 
                      $scope.refreshCart($scope.cartId); 
                  }); 
          }; 
      }); 
