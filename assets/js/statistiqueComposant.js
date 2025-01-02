document.addEventListener('DOMContentLoaded', function() {
    const options = {
        series: [{
            name: 'Moyenne',
            data: statistiquesData.map(item => item.moyenne)
        }, {
            name: 'Sortie Actuelle',
            data: statistiquesData.map(item => item.actuelle)
        }],
        chart: {
            type: 'bar',
            height: 500,
            stacked: false,
        },
        plotOptions: {
            bar: {
                horizontal: false,
                columnWidth: '55%',
                endingShape: 'rounded'
            },
        },
        dataLabels: {
            enabled: false
        },
        stroke: {
            show: true,
            width: 2,
            colors: ['transparent']
        },
        xaxis: {
            categories: statistiquesData.map(item => item.composant),
            title: {
                text: 'Composants'
            }
        },
        yaxis: {
            title: {
                text: 'Quantité'
            }
        },
        fill: {
            opacity: 1
        },
        tooltip: {
            y: {
                formatter: function (val) {
                    return val.toFixed(2)
                }
            }
        },
        title: {
            text: 'Comparaison des Sorties par Composant',
            align: 'center',
            style: {
                fontSize: '18px'
            }
        },
        colors: ['#008FFB', '#00E396'],
        legend: {
            position: 'top'
        }
    };

    const chart = new ApexCharts(document.querySelector("#statistique_composant"), options);
    chart.render();
});