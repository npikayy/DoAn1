
document.getElementById('print-button').addEventListener('click', () => {
    // Create a new window for printing
    const printWindow = window.open('', '_blank');
    
    // Get the table content
    const table = document.querySelector('table').cloneNode(true);
    
    // Create print-friendly styles
    const printStyles = `
        <style>
            body { font-family: Arial, sans-serif; }
            table { width: 100%; border-collapse: collapse; margin: 20px 0; }
            th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
            th { background-color: #f2f2f2; }
            @media print {
                @page { margin: 1cm; }
                .no-print { display: none; }
            }
        </style>
    `;
    
    // Set up the print window content
    printWindow.document.write(`
        <!DOCTYPE html>
        <html>
        <head>
            <title>Danh Sách Đề Tài</title>
            ${printStyles}
        </head>
        <body>
            <h1>Danh Sách Đề Tài</h1>
            ${table.outerHTML}
        </body>
        </html>
    `);
    
    // Wait for content to load then print
    printWindow.document.close();
    printWindow.onload = function() {
        printWindow.print();
        printWindow.onafterprint = function() {
            printWindow.close();
        };
    };
});

// Add required CSS animations
const style = document.createElement('style');
style.textContent = `
    @keyframes slideIn {
        from { transform: translateX(100%); opacity: 0; }
        to { transform: translateX(0); opacity: 1; }
    }
    
    @keyframes slideOut {
        from { transform: translateX(0); opacity: 1; }
        to { transform: translateX(100%); opacity: 0; }
    }
`;
document.head.appendChild(style);