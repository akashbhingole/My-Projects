IGApp.controller("LoginController", LoginController);

function LoginController($scope){
  $scope.login = function() {
    window.open('https://accounts.google.com/o/oauth2/auth?client_id=542508166568-rql17ltpe1frrl4sb8ar1c52l0380cas.apps.googleusercontent.com&redirect_uri=http://localhost:8080/InvoiceGeneratorApp/oauthCallback.html&scope=https://www.googleapis.com/auth/urlshortener&response_type=code&access_type=offline', '_self');
  }
}
