(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('listprojectController', listprojectController);

    listprojectController.$inject = ['$scope','$state', 'Projectcompletedetail'];

    function listprojectController($scope, $state, Projectcompletedetail) {
        var vm = this;
        vm.projectcompletedetail=[];

        loadAll();

        function loadAll() {

            Projectcompletedetail.query(function(result){
                vm.projectcompletedetail=result;
//                console.log(vm.projectcompletedetail[0].projectdetailDTO);
            });


        }


//        $http.get("/api/projectdetails").then(function(response) {
//           vm.prdetail = response.data;
//       });
    }
})();
