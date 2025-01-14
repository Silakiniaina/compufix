document.addEventListener('DOMContentLoaded', function() {
    const componentSelect = document.getElementById('componentSelect');
    const selectedComponentsDiv = document.getElementById('selectedComponents');
    const hiddenComponentInputs = document.getElementById('hiddenComponentInputs');
    const selectedComponents = new Set();

    componentSelect.addEventListener('change', function() {
        const selectedOption = this.options[this.selectedIndex];
        if (!selectedOption.value) return;

        const componentId = selectedOption.value;
        const componentName = selectedOption.dataset.name;

        if (!selectedComponents.has(componentId)) {
            // Ajouter le composant à l'ensemble
            selectedComponents.add(componentId);

            // Créer le tag visuel
            const tag = document.createElement('div');
            tag.className = 'component-tag';
            tag.innerHTML = `
                ${componentName}
                <button type="button" data-id="${componentId}">&times;</button>
            `;

            // Ajouter le champ caché pour le formulaire
            const hiddenInput = document.createElement('input');
            hiddenInput.type = 'hidden';
            hiddenInput.name = 'composant';
            hiddenInput.value = componentId;
            hiddenInput.id = `component-input-${componentId}`;
            hiddenComponentInputs.appendChild(hiddenInput);

            // Ajouter les événements de suppression
            tag.querySelector('button').addEventListener('click', function() {
                const componentId = this.dataset.id;
                selectedComponents.delete(componentId);
                tag.remove();
                document.getElementById(`component-input-${componentId}`).remove();
                
                // Réactiver l'option dans le select
                Array.from(componentSelect.options).forEach(option => {
                    if (option.value === componentId) {
                        option.disabled = false;
                    }
                });
            });

            selectedComponentsDiv.appendChild(tag);

            // Désactiver l'option sélectionnée
            selectedOption.disabled = true;
            componentSelect.value = '';
        }
    });
});