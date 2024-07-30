// DOM이 완전히 로드된 후 실행
        document.addEventListener('DOMContentLoaded', function() {
            // 현재 URL의 경로를 가져옴
            var currentPath = window.location.pathname;

            // 모든 메뉴 항목을 선택
            var menuItems = document.querySelectorAll('.admin-menu ul li a');

            // 각 메뉴 항목을 순회
            menuItems.forEach(function(menuItem) {
                // 메뉴 항목의 href 속성이 현재 경로와 일치하면 'active' 클래스를 추가
                if (menuItem.getAttribute('href') === currentPath) {
                    menuItem.classList.add('active');
                }
            });
        });

        var registrationChart;

        document.addEventListener('DOMContentLoaded', function () {
            var ctx = document.getElementById('registrationChart').getContext('2d');
            registrationChart = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
                    datasets: [
                        {
                            label: '50대 미만',
                            borderColor: 'blue',
                            backgroundColor: 'rgba(0, 0, 255, 0.1)',
                            data: []  
                        },
                        {
                            label: '50대',
                            borderColor: 'red',
                            backgroundColor: 'rgba(255, 0, 0, 0.1)',
                            data: []  
                        },
                        {
                            label: '60대',
                            borderColor: 'green',
                            backgroundColor: 'rgba(0, 255, 0, 0.1)',
                            data: []  
                        },
                        {
                            label: '70대',
                            borderColor: 'purple',
                            backgroundColor: 'rgba(128, 0, 128, 0.1)',
                            data: []  
                        },
                        {
                            label: '80대',
                            borderColor: 'orange',
                            backgroundColor: 'rgba(255, 165, 0, 0.1)',
                            data: []  
                        },
                        {
                            label: '90대 이상',
                            borderColor: 'brown',
                            backgroundColor: 'rgba(165, 42, 42, 0.1)',
                            data: []  
                        }
                    ]
                },
                options: {
                    responsive: true,
                    scales: {
                        x: {
                            beginAtZero: true
                        },
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });

            updateChart();
        });

        function updateChart() {
            var year = document.getElementById('yearSelect').value;

            var data = getDataForYear(year);

            registrationChart.data.datasets[0].data = data.under50;
            registrationChart.data.datasets[1].data = data.fifties;
            registrationChart.data.datasets[2].data = data.sixties;
            registrationChart.data.datasets[3].data = data.seventies;
            registrationChart.data.datasets[4].data = data.eighties;
            registrationChart.data.datasets[5].data = data.ninetiesAndAbove;
            registrationChart.update();
        }

        function getDataForYear(year) {
            var data = {
                "2023": {
                    under50: [12, 19, 3, 5, 2, 3, 8, 12, 6, 30, 5, 7],
                    fifties: [15, 29, 5, 10, 5, 6, 12, 18, 9, 5, 6, 8],
                    sixties: [10, 24, 5, 8, 6, 5, 10, 15, 8, 6, 5, 7],
                    seventies: [8, 20, 4, 7, 3, 5, 9, 13, 6, 4, 5, 6],
                    eighties: [5, 15, 3, 6, 2, 4, 7, 10, 5, 3, 4, 5],
                    ninetiesAndAbove: [2, 5, 1, 2, 1, 2, 3, 4, 2, 1, 2, 3]
                },
                "2022": {
                    under50: [22, 29, 5, 7, 4, 5, 10, 15, 9, 6, 7, 9],
                    fifties: [18, 25, 7, 12, 6, 8, 14, 20, 11, 7, 8, 10],
                    sixties: [15, 20, 6, 10, 5, 7, 12, 18, 10, 7, 6, 8],
                    seventies: [10, 18, 5, 8, 4, 6, 11, 15, 8, 5, 6, 7],
                    eighties: [8, 12, 4, 6, 3, 5, 8, 10, 6, 4, 5, 6],
                    ninetiesAndAbove: [3, 7, 2, 3, 1, 3, 5, 7, 3, 2, 3, 4]
                },
            };

            return data[year];
        }
        
        function searchMembers() {
            var category = document.getElementById("searchCategory").value;
            var query = document.getElementById("searchQuery").value;

            // Example:
            console.log("Searching for " + query + " in category " + category);
        }
        
        function searchCourses() {
            var category = document.getElementById("searchCategory").value;
            var query = document.getElementById("searchQuery").value;

            // Perform search logic here, for example:
            // - Make an AJAX call to the server to fetch filtered results
            // - Filter the existing rows in the table based on the search query

            // Example:
            console.log("Searching for " + query + " in category " + category);
        }