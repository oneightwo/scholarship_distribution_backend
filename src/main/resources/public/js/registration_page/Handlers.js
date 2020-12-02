const checkbox = document.getElementById('permission')
const submitButton = document.gedtElementById("submitButton")
checkbox.addEventListener('change', (event) => {
    submitButton.disabled = !event.target.checked;
})