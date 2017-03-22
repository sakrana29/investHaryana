(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectdetailcombinecodesController', ProjectdetailcombinecodesController);

    ProjectdetailcombinecodesController.$inject = ['Projectdetailcombinecodes'];

    function ProjectdetailcombinecodesController(Projectdetailcombinecodes) {

        var vm = this;

        vm.projectdetailcombinecodes = [];

        loadAll();

        function loadAll() {
            Projectdetailcombinecodes.query(function(result) {
                vm.projectdetailcombinecodes = result;
                vm.searchQuery = null;
            });
        }
    }
})();
