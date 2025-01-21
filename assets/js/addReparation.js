let composantCounter = 0;

// Ajout d'un composant
document.getElementById('addComposant').addEventListener('click', function() {
    const typeReparationSelect = document.getElementById('typeReparation');
    const technicienSelect = document.getElementById('technicien');
    const composantSelect = document.getElementById('composant');
    
    // Validation
    if (!typeReparationSelect.value || !technicienSelect.value || !composantSelect.value) {
        alert('Veuillez remplir tous les champs');
        return;
    }

    // Get selected text and values
    const typeReparationText = typeReparationSelect.options[typeReparationSelect.selectedIndex].text;
    const technicienText = technicienSelect.options[technicienSelect.selectedIndex].text;
    const composantText = composantSelect.options[composantSelect.selectedIndex].text;

    // Create list item
    const composantsList = document.getElementById('composantsList');
    const listItem = document.createElement('li');
    listItem.className = 'list-group-item';
    listItem.innerHTML = `
        <div class="d-flex justify-content-between align-items-center">
            <div>
                <strong>${composantText}</strong><br>
                <small class="text-muted">
                    Type: ${typeReparationText} | Technicien: ${technicienText}
                </small>
            </div>
            <button type="button" class="btn btn-danger btn-sm" onclick="removeComposant(this, ${composantCounter})">
                &times;
            </button>
        </div>
    `;
    composantsList.appendChild(listItem);

    // Add hidden inputs
    const hiddenInputs = document.getElementById('hiddenInputs');
    hiddenInputs.innerHTML += `
        <input type="hidden" name="composantReparation" value="${typeReparationSelect.value},${technicienSelect.value},${composantSelect.value}" data-index="${composantCounter}">
    `;

    // Show preview if hidden
    document.getElementById('composantsPreview').style.display = 'block';
    
    // Reset selects
    typeReparationSelect.value = '';
    technicienSelect.value = '';
    composantSelect.value = '';
    
    composantCounter++;
});

// Supprimer un composant
function removeComposant(button, index) {
    // Remove list item
    button.closest('li').remove();
    
    // Remove hidden inputs
    document.querySelectorAll(`#hiddenInputs input[data-index="${index}"]`).forEach(input => input.remove());
    
    // Hide preview if no more components
    if (document.getElementById('composantsList').children.length === 0) {
        document.getElementById('composantsPreview').style.display = 'none';
    }
}

// Toggle preview visibility
document.getElementById('closePreview').addEventListener('click', function() {
    document.getElementById('composantsPreview').style.display = 'none';
});

// Form submission validation
document.getElementById('reparationForm').addEventListener('submit', function(e) {
    if (composantCounter === 0) {
        e.preventDefault();
        alert('Veuillez ajouter au moins un composant à réparer');
    }
});