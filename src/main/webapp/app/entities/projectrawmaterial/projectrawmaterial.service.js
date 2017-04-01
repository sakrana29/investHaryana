(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Projectrawmaterial', Projectrawmaterial);

    Projectrawmaterial.$inject = ['$resource', 'DateUtils'];

    function Projectrawmaterial ($resource, DateUtils) {
        var resourceUrl =  'api/projectrawmaterials/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.createdate = DateUtils.convertDateTimeFromServer(data.createdate);
                        data.updatedate = DateUtils.convertDateTimeFromServer(data.updatedate);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
