const money_td = document.querySelector('.money-td');
const money = parseInt(money_td.innerText);
money_td.innerText = money.toLocaleString() + ' 원';

const ctx = document.querySelector('.bar-chart-div');

new Chart(ctx, {
type: 'bar',
data: {
    labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
    datasets: [{
    label: '# of Votes',
    data: [12, 19, 3, 5, 2, 3],
    borderWidth: 1
    }]
},
options: {
    scales: {
    y: {
        beginAtZero: true
    }
    }
}
});
