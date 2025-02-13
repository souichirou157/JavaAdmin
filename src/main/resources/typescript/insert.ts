

const table = document.getElementById("insertTable") as HTMLTableElement | null;

if (table) {
    const td = table.rows[1].cells[4];
    const input = td.firstElementChild as HTMLInputElement | null;

    if (input) input.value = 'NULL';

}


const btn = window.document.getElementById("insert_submit") as HTMLInputElement | null;
const con_sugest = window.document.getElementById('continue') as HTMLElement | null;
const end_sugest = window.document.getElementById('end') as HTMLElement | null;

if(btn && con_sugest && end_sugest){
	
	con_sugest.style.display = 'none';
	end_sugest.style.display = 'none';
	
	btn.addEventListener("input",()=>{
		con_sugest.style.display = '';
		end_sugest.style.display = ''
		console.log('test');
	})
}