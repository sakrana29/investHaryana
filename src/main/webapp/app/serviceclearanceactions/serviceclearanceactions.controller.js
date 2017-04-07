(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('serviceClearanceActionController', serviceClearanceActionController);

    serviceClearanceActionController.$inject = ['$timeout', '$scope','projectid', '$stateParams','$state', 'Projectcompletedetail', 'Auth', 'Principal', 'DepartmentService', 'Projectservicedetail','ProjectservicedetailbyProject'];

    function serviceClearanceActionController ($timeout, $scope, projectid, $stateParams, $state, Projectcompletedetail, Auth, Principal, DepartmentService, Projectservicedetail,ProjectservicedetailbyProject) {
        var vm = this;
        vm.projectid=projectid;

        loadProjectServices();
        function loadProjectServices() {
            vm.projectservicelist= ProjectservicedetailbyProject.query({id:vm.projectid});
            console.log(vm.projectservicelist);
        }

        Principal.identity().then(function(account) {
        vm.account = account;
        });

        vm.markRequired=markRequired;
        function markRequired(serviceid,projectid)
        {
            alert(serviceid);
            alert(projectid);
        }


    }
})();
