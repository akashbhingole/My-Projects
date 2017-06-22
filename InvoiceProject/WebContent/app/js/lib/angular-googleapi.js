angular.module('googleApi', [])
	.value('version', '0.1')

    .service("googleApiBuilder", function($q) {
        this.loadClientCallbacks = [];

        this.build = function(requestBuilder, responseTransformer) {
            return function(args) {
                var deferred = $q.defer();
                var response;
                request = requestBuilder(args);
                request.execute(function(resp, raw) {
                    if(resp.error) {
                        deferred.reject(resp.error);
                    } else {
                        response = responseTransformer ? responseTransformer(resp) : resp;
                        deferred.resolve(response);
                    }

                });
                return deferred.promise;

            }
        };

        this.afterClientLoaded = function(callback) {
            this.loadClientCallbacks.push(callback);
        };

        this.runClientLoadedCallbacks = function() {
            for(var i=0; i < this.loadClientCallbacks.length; i++) {
                this.loadClientCallbacks[i]();
            }
        };
    })

    .provider('googleLogin', function() {

        this.configure = function(conf) {
            this.config = conf;
        };

        this.$get = function ($q, googleApiBuilder, $rootScope) {
            var config = this.config;
            var deferred = $q.defer();
            return {
                login: function () {
                    gapi.auth.authorize({ client_id: config.clientId, scope: config.scopes, immediate: false}, this.handleAuthResult);

                    return deferred.promise;
                },

                handleClientLoad: function () {
                    gapi.auth.init(function () { });
                    window.setTimeout(checkAuth, 1);
                },

                checkAuth: function() {
                    gapi.auth.authorize({ client_id: clientId, scope: scopes, immediate: true }, this.handleAuthResult );
                },

                handleAuthResult: function(authResult) {
                    if (authResult && !authResult.error) {
                        var data = {};
                        $rootScope.$broadcast("google:authenticated", authResult);
                        googleApiBuilder.runClientLoadedCallbacks();
												console.log("========="); console.log(authResult);
                        deferred.resolve(data);
                    } else {
                        deferred.reject(authResult.error);
                    }
                },

								getInfo: function() {
									gapi.client.load('plus', 'v1').then(function() {
					          // Step 5: Assemble the API request
					          var request = gapi.client.plus.people.get({
					            'userId': 'me'
					          });
					          // Step 6: Execute the API request
					          request.then(function(resp) {
					            var heading = document.createElement('h4');
					            var image = document.createElement('img');
					            image.src = resp.result.image.url;
					            heading.appendChild(image);
					            heading.appendChild(document.createTextNode(resp.result.displayName));

					            document.getElementById('content').appendChild(heading);
					          }, function(reason) {
					            console.log('Error: ' + reason.result.error.message);
					          });
					        });
								}
            }
        };


    })

    .service("googleCalendar", function(googleApiBuilder, $rootScope) {

        var self = this;
        var itemExtractor = function(resp) { return resp.items; };

        googleApiBuilder.afterClientLoaded(function() {
            gapi.client.load('calendar', 'v3', function() {
                self.listEvents = googleApiBuilder.build(gapi.client.calendar.events.list, itemExtractor);
                self.listCalendars = googleApiBuilder.build(gapi.client.calendar.calendarList.list, itemExtractor);
                self.createEvent = googleApiBuilder.build(gapi.client.calendar.events.insert);
                $rootScope.$broadcast("googleCalendar:loaded")
            });

        });

    });
