(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ApprovalformsController', ApprovalformsController);

    ApprovalformsController.$inject = ['Approvalforms'];

    function ApprovalformsController(Approvalforms) {

        var vm = this;

        vm.approvalforms = [];

        loadAll();

        function loadAll() {
            Approvalforms.query(function(result) {
                vm.approvalforms = result;
                vm.searchQuery = null;
            });
        }
    }
})();
