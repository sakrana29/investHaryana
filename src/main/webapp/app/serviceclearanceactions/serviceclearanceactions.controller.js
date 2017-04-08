(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('serviceClearanceActionController', serviceClearanceActionController);

    serviceClearanceActionController.$inject = ['$timeout', '$scope','projectservicelist', '$stateParams','$state', 'Projectcompletedetail', 'Auth', 'Principal', 'DepartmentService', 'Projectservicedetail','ProjectservicedetailbyProject'];

    function serviceClearanceActionController ($timeout, $scope, projectservicelist, $stateParams, $state, Projectcompletedetail, Auth, Principal, DepartmentService, Projectservicedetail,ProjectservicedetailbyProject) {
        var vm = this;
        vm.projectservicelist=projectservicelist;

        Principal.identity().then(function(account) {
        vm.account = account;
        });

        vm.markRequired=markRequired;
        function markRequired(projectservice)
        {
            projectservice.isRequired=true;
            Projectservicedetail.save(projectservice,onMarkSuccess,onMarkError);
        }
        function onMarkSuccess()
        {

        }
        function onMarkError()
        {

        }


    }
})();
