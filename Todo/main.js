
const list = document.getElementById("list");
const createBtn = document.getElementById("create-btn");

let todos = [];

createBtn.addEventListener('click', createNewTodo);

function createNewTodo() {

	const item = {
		id: new Date().getTime(),
		text: "",
		complete: false
	}

	todos.unshift(item);
	const { itemEl, inputEl } = createTodoElement(item);
	list.prepend(itemEl);
	inputEl.removeAttribute("disabled");
	inputEl.focus();

	saveToLocalStorage();
}

function createTodoElement(item) {
	const itemEl = document.createElement("div");
	const checkbox = document.createElement("input");
	const inputEl = document.createElement("input");
	const actionsEl = document.createElement("div");
	const editBtnEl = document.createElement("button");
	const removeBtnEl = document.createElement("button");

	itemEl.classList.add("item");
	checkbox.type = "checkbox";
	checkbox.checked = item.complete;

	if (item.complete) {
		itemEl.classList.add("complete");
	}

	inputEl.type = "text";
	inputEl.value = item.text;
	inputEl.setAttribute("disabled", "");

	actionsEl.classList.add("actions");

	editBtnEl.classList.add("material-icons");
	editBtnEl.innerText = "edit";
	removeBtnEl.classList.add("material-icons", "remove-btn");
	removeBtnEl.innerText = "remove_circle";

	actionsEl.append(editBtnEl, removeBtnEl); 
	itemEl.append(checkbox, inputEl, actionsEl);

	checkbox.addEventListener("change", () => {
		item.complete = checkbox.checked;

		if (item.complete) {
			itemEl.classList.add("complete");
		} else {
			itemEl.classList.remove("complete");
		}

		saveToLocalStorage();
	});

	inputEl.addEventListener("input", () => {
	});

	inputEl.addEventListener("blur", () => {
		inputEl.setAttribute("disabled", "");
		item.text = inputEl.value;
		saveToLocalStorage();
	});

	editBtnEl.addEventListener("click", () => {
		inputEl.removeAttribute("disabled");
		inputEl.focus();
	});

	removeBtnEl.addEventListener("click", () => {
		todos = todos.filter(todo => todo.id !== item.id);
		itemEl.remove();

		saveToLocalStorage();
	});

	return { itemEl, inputEl }
}

function displayTodos() {
	loadFromLocalStorage();

	for (let i = 0; i < todos.length; i++) {
		const item = todos[i];

		const { itemEl } = createTodoElement(item);

		list.append(itemEl);
	}
}

displayTodos();

function saveToLocalStorage() {
	const data = JSON.stringify(todos);

	localStorage.setItem("myTodos", data);
}

function loadFromLocalStorage() {
	const data = localStorage.getItem("myTodos");

	if (data) {
		todos = JSON.parse(data);
	}
}