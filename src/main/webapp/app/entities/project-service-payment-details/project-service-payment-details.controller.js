(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectServicePaymentDetailsController', ProjectServicePaymentDetailsController);

    ProjectServicePaymentDetailsController.$inject = ['ProjectServicePaymentDetails'];

    function ProjectServicePaymentDetailsController(ProjectServicePaymentDetails) {

        var vm = this;

        vm.projectServicePaymentDetails = [];

        loadAll();

        function loadAll() {
            ProjectServicePaymentDetails.query(function(result) {
                vm.projectServicePaymentDetails = result;
                vm.searchQuery = null;
            });
        }
    }
})();
