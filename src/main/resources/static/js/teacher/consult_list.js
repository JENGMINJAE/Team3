document.addEventListener('DOMContentLoaded', function() {
    var Calendar = FullCalendar.Calendar;

    var calendarEl = document.getElementById('calendar');

    var calendar = new Calendar(calendarEl, {
      headerToolbar: {
        left : '',
        center: 'title',
        right: 'prev,next today'
      }
    });
  
    calendar.render();
  });