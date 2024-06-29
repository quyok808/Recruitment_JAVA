import createTagCloud from './tagcloud.js';

document.addEventListener('DOMContentLoaded', () => {
   const options = {
           radius: 200,
           maxSpeed: 'slow',  // Set the speed to slow for a very slow rotation
           initSpeed: 'normal',
           deceleration: 0.95,
           keep: true,
           containerClass: 'tag-cloud-container',
           itemClass: 'tag-cloud-item',
           useContainerInlineStyles: true,
           useItemInlineStyles: true,
           useHTML: true
       };

    // Placeholder texts array
    let texts = [];

    // Assuming you fetch data asynchronously (e.g., from a server)
    fetch('TAGCLOUD/texts.txt')
        .then(response => response.text())
        .then(data => {
            // Convert data to array of lines
            const lines = data.trim().split('\n');
            // Create texts array with 25 elements from lines
            for (let i = 0; i < 25; i++) {
                texts.push(lines[i % lines.length].trim());
            }
            // Call createTagCloud with fetched texts
            createTagCloud('#tag-cloud-container', texts, options);
        })
        .catch(error => console.error('Error fetching data:', error));
});