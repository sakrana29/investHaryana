(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('serviceClearanceActionController', serviceClearanceActionController);

    serviceClearanceActionController.$inject = ['$timeout', '$scope', '$stateParams','$state', 'Projectcompletedetail', 'Auth', 'Principal', 'DepartmentService', 'Projectservicedetail','ProjectservicedetailbyProject'];

    function serviceClearanceActionController ($timeout, $scope, $stateParams, $state, Projectcompletedetail, Auth, Principal, DepartmentService, Projectservicedetail,ProjectservicedetailbyProject) {
        var vm = this;

        loadProjectServices();
        function loadProjectServices()
        {
            vm.projectservicelist=ProjectservicedetailbyProject.query({id : $stateParams.id});
        }

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
            loadProjectServices();
        }
        function onMarkError()
        {

        }
    }
})();
