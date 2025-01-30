let composantCounter = 0;

// Validation function for selects
function validateSelects() {
    const typeReparationSelect = document.getElementById('typeReparation');
    const technicienSelect = document.getElementById('technicien');
    const composantSelect = document.getElementById('composant');
    const prixInput = document.getElementById('prix');
    
    if (!typeReparationSelect.value) {
        alert('Veuillez sélectionner un type de réparation');
        return false;
    }
    if (!technicienSelect.value) {
        alert('Veuillez sélectionner un technicien');
        return false;
    }
    if (!composantSelect.value) {
        alert('Veuillez sélectionner un composant');
        return false;
    }
    if (!prixInput.value || prixInput.value <= 0) {
        if (!prixInput.disabled) { // Only validate prix if it's not disabled
            alert('Veuillez entrer un prix valide');
            return false;
        }
    }
    return true;
}

// Function to handle typeReparation change
function handleTypeReparationChange() {
    const typeReparationSelect = document.getElementById('typeReparation');
    const prixInput = document.getElementById('prix');

    if (typeReparationSelect.value == "1") { 
        prixInput.disabled = true; 
        prixInput.value = ""; 
    } else {
        prixInput.disabled = false; 
    }
}

// Add event listener to typeReparationSelect to handle changes
document.getElementById('typeReparation').addEventListener('change', handleTypeReparationChange);

// Ajout d'un composant
document.getElementById('addComposant').addEventListener('click', function() {
    const typeReparationSelect = document.getElementById('typeReparation');
    const technicienSelect = document.getElementById('technicien');
    const composantSelect = document.getElementById('composant');
    const prixInput = document.getElementById('prix');
    
    // Validate selects before adding
    if (!validateSelects()) {
        return;
    }

    // Get selected text and values
    const typeReparationText = typeReparationSelect.options[typeReparationSelect.selectedIndex].text;
    const technicienText = technicienSelect.options[technicienSelect.selectedIndex].text;
    const composantText = composantSelect.options[composantSelect.selectedIndex].text;
    const prix = parseFloat(prixInput.value).toFixed(2);

    // Create list item
    const composantsList = document.getElementById('composantsList');
    const listItem = document.createElement('li');
    listItem.className = 'list-group-item';
    listItem.innerHTML = `
        <div class="d-flex justify-content-between align-items-center">
            <div>
                <strong>${composantText}</strong><br>
                <small class="text-muted">
                    Type: ${typeReparationText} | Technicien: ${technicienText} | Prix: ${prix} €
                </small>
            </div>
            <button type="button" class="btn btn-danger btn-sm" onclick="removeComposant(this, ${composantCounter})">
                &times;
            </button>
        </div>
    `;
    composantsList.appendChild(listItem);

    // Add hidden input
    const hiddenInputs = document.getElementById('hiddenInputs');
    hiddenInputs.innerHTML += `
        <input type="hidden" name="composantReparation" value="${typeReparationSelect.value},${technicienSelect.value},${composantSelect.value},${prix}" data-index="${composantCounter}">
    `;

    // Show preview if hidden
    document.getElementById('composantsPreview').style.display = 'block';
    
    // Reset inputs
    typeReparationSelect.value = '';
    technicienSelect.value = '';
    composantSelect.value = '';
    prixInput.value = '';
    prixInput.disabled = false; // Re-enable prix input after adding
    
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

// Form submission validation - only check if components exist
document.getElementById('reparationForm').addEventListener('submit', function(e) {
    if (composantCounter === 0) {
        e.preventDefault();
        alert('Veuillez ajouter au moins un composant à réparer');
    }
});

// Call handleTypeReparationChange initially to set the correct state of prixInput
handleTypeReparationChange();