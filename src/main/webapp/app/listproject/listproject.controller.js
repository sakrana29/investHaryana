(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('listprojectController', listprojectController);

    listprojectController.$inject = ['$scope','$state', 'Projectlist'];

    function listprojectController($scope, $state, Projectlist) {
        var vm = this;
        vm.projectcompletedetail=[];

        loadAll();

        function loadAll() {

            Projectlist.query(function(result){
                vm.projectlist=result;
//                console.log(vm.projectcompletedetail[0].projectdetailDTO);
            });


        }


//        $http.get("/api/projectdetails").then(function(response) {
//           vm.prdetail = response.data;
//       });
    }
})();
