LoginApp.service("googleService", googleService);

function googleService($q) {
  var obj = {}
  obj.init = function() {
      OAuth.initialize('GuZs0rLTZUx7SiVRT1PAPMCjDN8');
  };

  obj.login = function() {
    var deferred = $q.defer();

    OAuth.popup('google', function(err, google) {
      google.get('/oauth2/v1/userinfo?alt=json').done(function(data) {
        console.log(data);
        deferred.resolve(data.name);
      });
    });

    return deferred.promise;
  };

  return obj;
}
