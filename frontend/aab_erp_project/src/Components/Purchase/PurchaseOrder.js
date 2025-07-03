import { React, useState, useRef } from 'react'
import edit from '../Images/Edit.svg';
import deleteIcon from '../Images/Delete.svg';
import Select from 'react-select';

const PurchaseOrder = () => {
    const [itemName, setItemName] = useState('');
    const [model, setModel] = useState('');
    const [brand, setBrand] = useState('');
    const [type, setType] = useState('');
    const [quantity, setQuantity] = useState('');
    const [items, setItems] = useState([]);
    const [editIndex, setEditIndex] = useState(null);
    const [editedItem, setEditedItem] = useState({});
    const [selectedVendor, setSelectedVendor] = useState('');
    const [selectedSite, setSelectedSite] = useState('');
    const [selectedCategory, setSelectedCategory] = useState('');
    const [itemNameOptions, setItemNameOptions] = useState([]);
    const [modelOptions, setModelOptions] = useState([]);
    const [brandOptions, setBrandOptions] = useState([]);
    const [typeOptions, setTypeOptions] = useState([]);

    const [selectedItemName, setSelectedItemName] = useState(null);
    const [selectedModel, setSelectedModel] = useState(null);
    const [selectedBrand, setSelectedBrand] = useState(null);
    const [selectedType, setSelectedType] = useState(null);
    const scrollRef = useRef(null);
    const isDragging = useRef(false);
    const start = useRef({ x: 0, y: 0 });
    const scroll = useRef({ left: 0, top: 0 });
    const velocity = useRef({ x: 0, y: 0 });
    const animationFrame = useRef(null);
    const lastMove = useRef({ time: 0, x: 0, y: 0 });


    const handleMouseDown = (e) => {
        isDragging.current = true;
        start.current = { x: e.clientX, y: e.clientY };
        scroll.current = {
            left: scrollRef.current.scrollLeft,
            top: scrollRef.current.scrollTop,
        };
        lastMove.current = {
            time: Date.now(),
            x: e.clientX,
            y: e.clientY,
        };
        scrollRef.current.style.cursor = 'grabbing';
        scrollRef.current.style.userSelect = 'none';
        cancelMomentum();
    };
    const handleMouseMove = (e) => {
        if (!isDragging.current) return;
        const dx = e.clientX - start.current.x;
        const dy = e.clientY - start.current.y;
        const now = Date.now();
        const dt = now - lastMove.current.time || 16;
        velocity.current = {
            x: (e.clientX - lastMove.current.x) / dt,
            y: (e.clientY - lastMove.current.y) / dt,
        };
        scrollRef.current.scrollLeft = scroll.current.left - dx;
        scrollRef.current.scrollTop = scroll.current.top - dy;
        lastMove.current = {
            time: now,
            x: e.clientX,
            y: e.clientY,
        };
    };
    const handleMouseUp = () => {
        if (!isDragging.current) return;
        isDragging.current = false;
        scrollRef.current.style.cursor = '';
        scrollRef.current.style.userSelect = '';
        applyMomentum();
    };
    const cancelMomentum = () => {
        if (animationFrame.current) {
            cancelAnimationFrame(animationFrame.current);
            animationFrame.current = null;
        }
    };
    const applyMomentum = () => {
        const friction = 0.95;
        const minVelocity = 0.1;
        const step = () => {
            const { x, y } = velocity.current;
            if (Math.abs(x) > minVelocity || Math.abs(y) > minVelocity) {
                scrollRef.current.scrollLeft -= x * 20;
                scrollRef.current.scrollTop -= y * 20;
                velocity.current.x *= friction;
                velocity.current.y *= friction;
                animationFrame.current = requestAnimationFrame(step);
            } else {
                cancelMomentum();
            }
        };
        animationFrame.current = requestAnimationFrame(step);
    };
    const vendorOptionsList = [
        { value: 'VendorA', label: 'Vendor A' },
        { value: 'VendorB', label: 'Vendor B' },
        { value: 'VendorC', label: 'Vendor C' },
    ];
    const categoryOptions = [
        { value: 'carpentry', label: 'Carpentry' },
        { value: 'plumbing', label: 'Plumbing' },
        { value: 'electrical', label: 'Electrical' },
    ];
    const optionsMap = {
        carpentry: {
            itemNames: [
                { value: 'Item C1', label: 'Item C1' },
                { value: 'Item C2', label: 'Item C2' },
            ],
            models: [
                { value: 'Model CX', label: 'Model CX' },
                { value: 'Model CY', label: 'Model CY' },
            ],
            brands: [
                { value: 'Brand CA', label: 'Brand CA' },
                { value: 'Brand CB', label: 'Brand CB' },
            ],
            types: [
                { value: 'Type C1', label: 'Type C1' },
                { value: 'Type C2', label: 'Type C2' },
            ],
        },
        plumbing: {
            itemNames: [
                { value: 'Item P1', label: 'Item P1' },
                { value: 'Item P2', label: 'Item P2' },
            ],
            models: [
                { value: 'Model PX', label: 'Model PX' },
                { value: 'Model PY', label: 'Model PY' },
            ],
            brands: [
                { value: 'Brand PA', label: 'Brand PA' },
                { value: 'Brand PB', label: 'Brand PB' },
            ],
            types: [
                { value: 'Type P1', label: 'Type P1' },
                { value: 'Type P2', label: 'Type P2' },
            ],
        },
        electrical: {
            itemNames: [
                { value: 'Item E1', label: 'Item E1' },
                { value: 'Item E2', label: 'Item E2' },
            ],
            models: [
                { value: 'Model EX', label: 'Model EX' },
                { value: 'Model EY', label: 'Model EY' },
            ],
            brands: [
                { value: 'Brand EA', label: 'Brand EA' },
                { value: 'Brand EB', label: 'Brand EB' },
            ],
            types: [
                { value: 'Type E1', label: 'Type E1' },
                { value: 'Type E2', label: 'Type E2' },
            ],
        },
    };
    const handleCategoryChange = (selectedOption) => {
        setSelectedCategory(selectedOption?.value || '');
        const categoryData = optionsMap[selectedOption?.value];

        if (categoryData) {
            setItemNameOptions(categoryData.itemNames);
            setModelOptions(categoryData.models);
            setBrandOptions(categoryData.brands);
            setTypeOptions(categoryData.types);
        } else {
            setItemNameOptions([]);
            setModelOptions([]);
            setBrandOptions([]);
            setTypeOptions([]);
        }
    };
    const getOptions = (field) => {
        switch (field) {
            case 'category':
                return categoryOptions;
            case 'itemName':
                return itemNameOptions;
            case 'model':
                return modelOptions;
            case 'brand':
                return brandOptions;
            case 'type':
                return typeOptions;
            default:
                return [];
        }
    };
    const handleFieldChange = (field, value) => {
        setEditedItem({
            ...editedItem,
            [field]: value,
        });
    };
    const customStyles = {
        control: (provided, state) => ({
            ...provided,
            borderWidth: '2px',
            borderRadius: '8px',
            borderColor: state.isFocused ? '#FAF6ED' : '#FAF6ED',
            boxShadow: state.isFocused ? '0 0 0 1px #FAF6ED' : 'none',
            '&:hover': {
                borderColor: '#FAF6ED',
            }
        }),
    };
    const siteOptions = [
        { value: 'Site1', label: 'Site 1' },
        { value: 'Site2', label: 'Site 2' },
        // Add more sites here
    ];
    const fieldMap = {
        selectcategory: 'category',
        itemName: 'itemName',
        model: 'model',
        brand: 'brand',
        type: 'type',
    };
    const handleAddItem = () => {
        if (selectedItemName && selectedCategory && selectedModel && selectedBrand && selectedType && quantity) {
            setItems([
                ...items,
                {
                    itemName: selectedItemName?.label,
                    category: selectedCategory?.label,
                    model: selectedModel?.label,
                    brand: selectedBrand?.label,
                    type: selectedType?.label,
                    quantity,
                    amount: 0,
                },
            ]);

            // Keep the selectedCategory, clear rest:
            setSelectedItemName(null);
            setSelectedModel(null);
            setSelectedBrand(null);
            setSelectedType(null);
            setQuantity('');
        }
    };

    return (
        <div>
            <div className="mx-auto p-6 border-collapse lg:w-[1870px] bg-[#FFFFFF] ml-6 mr-6 rounded-md ">
                <div className=" grid grid-cols-1 lg:grid-cols-[270px_323px_168px_149px_270px] gap-6">
                    <div className="mt-2">
                        <h4 className="text-left font-bold mb-2 ">Vendor Name</h4>
                        <Select
                            value={vendorOptionsList.find(option => option.value === selectedVendor)}
                            onChange={(selectedOption) => {
                                const value = selectedOption?.value || '';
                                setSelectedVendor(value);
                                setItemName('');
                                setSelectedCategory('');
                                setModel('');
                                setBrand('');
                                setType('');
                            }}
                            options={vendorOptionsList}
                            placeholder="Select Vendor"
                            isSearchable
                            isClearable
                            className="w-[270px]"
                            styles={customStyles}
                        />
                    </div>
                    <div className="mt-2 text-left ">
                        <h4 className="font-bold mb-2 ">Project Name</h4>
                        <Select
                            value={siteOptions.find(option => option.value === selectedSite)}
                            onChange={(selectedOption) => setSelectedSite(selectedOption?.value || '')}
                            options={siteOptions}
                            placeholder="Select Site Name..."
                            isSearchable
                            isClearable
                            className="w-[270px] lg:w-[323px]"
                            styles={customStyles}
                        />
                    </div>
                    <div className="text-left mt-2">
                        <h4 className=" font-bold mb-2">Date</h4>
                        <input
                            type="date"
                            className="border-2 border-[#BF9853] border-opacity-[20%] focus:outline-none w-[168px] h-[45px] rounded-lg px-4 py-2 cursor-pointer"
                        />
                    </div>
                    <div className="text-left">
                        <h4 className=" mt-2.5 font-bold">PO.No</h4>
                        <input
                            type="number"
                            className="border-2 border-[#BF9853] border-opacity-[5%] focus:outline-none bg-[#F2F2F2] rounded-lg lg:w-36 w-[149px] h-[45px] mt-2 px-2 py-2 appearance-none no-spinner"
                        />
                    </div>
                    <div className="text-left">
                        <h4 className=" mt-2.5 font-bold mb-2 ml-4">Project Incharge</h4>
                        <select
                            placeholder="Select the file..."
                            className="border-2 border-[#BF9853] border-opacity-[20%] focus:outline-none rounded-lg lg:w-60 cursor-pointer w-[270px] h-[45px]"
                        />
                    </div>
                </div>
            </div>
            {selectedVendor && selectedSite && (
                <div className="bg-white rounded w-[1870px] ml-6 mt-3 p-6">
                    <div className="lg:flex gap-10">
                        {/* Form */}
                        <div className="space-y-6 text-left">
                            <div>
                                <label className="block font-semibold mb-2">Category</label>
                                <Select
                                    value={categoryOptions.find(opt => opt.value === selectedCategory)}
                                    onChange={(option) => {
                                        handleCategoryChange(option);
                                        setSelectedCategory(option);
                                    }}
                                    options={categoryOptions}
                                    placeholder="Select Category"
                                    isSearchable
                                    isClearable
                                    styles={customStyles}
                                    className="w-[294px]"
                                />
                            </div>
                            <div>
                                <label className="block font-semibold mb-2">Item Name</label>
                                <Select
                                    value={selectedItemName}
                                    onChange={(option) => setSelectedItemName(option)}
                                    options={itemNameOptions}
                                    placeholder="Select Item Name"
                                    isSearchable
                                    isClearable
                                    styles={customStyles}
                                    className="w-[294px]"
                                />
                            </div>
                            <div>
                                <label className="block font-semibold mb-2">Model</label>
                                <Select
                                    value={selectedModel}
                                    onChange={(option) => setSelectedModel(option)}
                                    options={modelOptions}
                                    placeholder="Select Model"
                                    isSearchable
                                    isClearable
                                    styles={customStyles}
                                    className="w-[294px]"
                                />
                            </div>
                            <div>
                                <label className="block font-semibold mb-2">Brand</label>
                                <Select
                                    value={selectedBrand}
                                    onChange={(option) => setSelectedBrand(option)}
                                    options={brandOptions}
                                    placeholder="Select Brand"
                                    isSearchable
                                    isClearable
                                    styles={customStyles}
                                    className="w-[294px]"
                                />
                            </div>
                            <div>
                                <label className="block font-semibold mb-2">Type</label>
                                <Select
                                    value={selectedType}
                                    onChange={(option) => setSelectedType(option)}
                                    options={typeOptions}
                                    placeholder="Select Type"
                                    isSearchable
                                    isClearable
                                    styles={customStyles}
                                    className="w-[294px]"
                                />
                            </div>
                            <div>
                                <label className="block font-semibold mb-2">Quantity</label>
                                <input
                                    type="number"
                                    value={quantity}
                                    onChange={(e) => setQuantity(e.target.value)}
                                    placeholder="Enter Qty"
                                    className="w-[149px] h-[45px] pl-3 border-2 border-[#BF9853] border-opacity-[20%] focus:outline-none rounded-lg no-spinner"
                                />
                            </div>
                            <button
                                onClick={handleAddItem}
                                className="w-[80px] h-[35px] text-white rounded bg-[#BF9853]"
                            >
                                Add
                            </button>
                        </div>
                        {/* Table */}
                        <div className='mt-3'>
                            <div className="text-sm font-bold mb-2 text-right">Export PDF</div>
                            <div
                                ref={scrollRef}
                                className="w-full rounded-lg border border-gray-200 border-l-8 border-l-[#BF9853]  overflow-scroll select-none"
                                onMouseDown={handleMouseDown}
                                onMouseMove={handleMouseMove}
                                onMouseUp={handleMouseUp}
                                onMouseLeave={handleMouseUp}
                            >
                                <table className="text-left lg:w-[1100px] w-screen text-base">
                                    <thead className="bg-[#FAF6ED] text-left">
                                        <tr className="border-b border-[#e6e1d1]">
                                            <th className="py-2 px-3">S.No</th>
                                            <th className="py-2 px-3">Item Name</th>
                                            <th className="py-2 px-3">Category</th>
                                            <th className="py-2 px-3">Model</th>
                                            <th className="py-2 px-3">Brand</th>
                                            <th className="py-2 px-3">Type</th>
                                            <th className="py-2 px-3">Qty</th>
                                            <th className="py-2 px-3">Amount</th>
                                            <th className="py-2 px-3">Activity</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {items.map((item, index) => (
                                            <tr key={index} className="border-b border-[#e6e1d1]">
                                                <td className="py-2 px-3 font-semibold">{index + 1}</td>

                                                {/* Item Name */}
                                                <td className="py-2 px-3 font-semibold">
                                                    {editIndex === index ? (
                                                        <Select
                                                            value={editedItem.itemName ? { label: editedItem.itemName, value: editedItem.itemName } : null}
                                                            onChange={(option) =>
                                                                setEditedItem((prev) => ({ ...prev, itemName: option ? option.value : "" }))
                                                            }
                                                            options={itemNameOptions}
                                                            isSearchable
                                                            isClearable
                                                            menuPortalTarget={document.body}
                                                            styles={customStyles}
                                                            className="w-[150px]"
                                                        />
                                                    ) : (
                                                        item.itemName
                                                    )}
                                                </td>

                                                {/* Category */}
                                                <td className="py-2 px-3 font-semibold">
                                                    {editIndex === index ? (
                                                        <Select
                                                            value={editedItem.category ? { label: editedItem.category, value: editedItem.category } : null}
                                                            onChange={(option) =>
                                                                setEditedItem((prev) => ({ ...prev, category: option ? option.value : "" }))
                                                            }
                                                            options={categoryOptions}
                                                            isSearchable
                                                            menuPortalTarget={document.body}
                                                            styles={customStyles}
                                                            className="w-[150px]"
                                                        />
                                                    ) : (
                                                        item.category
                                                    )}
                                                </td>

                                                {/* Model */}
                                                <td className="py-2 px-3 font-semibold">
                                                    {editIndex === index ? (
                                                        <Select
                                                            value={editedItem.model ? { label: editedItem.model, value: editedItem.model } : null}
                                                            onChange={(option) =>
                                                                setEditedItem((prev) => ({ ...prev, model: option ? option.value : "" }))
                                                            }
                                                            options={modelOptions}
                                                            isSearchable
                                                            isClearable
                                                            menuPortalTarget={document.body}
                                                            styles={customStyles}
                                                            className="w-[150px]"
                                                        />
                                                    ) : (
                                                        item.model
                                                    )}
                                                </td>

                                                {/* Brand */}
                                                <td className="py-2 px-3 font-semibold">
                                                    {editIndex === index ? (
                                                        <Select
                                                            value={editedItem.brand ? { label: editedItem.brand, value: editedItem.brand } : null}
                                                            onChange={(option) =>
                                                                setEditedItem((prev) => ({ ...prev, brand: option ? option.value : "" }))
                                                            }
                                                            options={brandOptions}
                                                            isSearchable
                                                            isClearable
                                                            menuPortalTarget={document.body}
                                                            styles={customStyles}
                                                            className="w-[150px]"
                                                        />
                                                    ) : (
                                                        item.brand
                                                    )}
                                                </td>

                                                {/* Type */}
                                                <td className="py-2 px-3 font-semibold">
                                                    {editIndex === index ? (
                                                        <Select
                                                            value={editedItem.type ? { label: editedItem.type, value: editedItem.type } : null}
                                                            onChange={(option) =>
                                                                setEditedItem((prev) => ({ ...prev, type: option ? option.value : "" }))
                                                            }
                                                            options={typeOptions}
                                                            isSearchable
                                                            isClearable
                                                            menuPortalTarget={document.body}
                                                            styles={customStyles}
                                                            className="w-[150px]"
                                                        />
                                                    ) : (
                                                        item.type
                                                    )}
                                                </td>

                                                {/* Quantity */}
                                                <td className="py-2 px-3 font-semibold">
                                                    {editIndex === index ? (
                                                        <input
                                                            type="number"
                                                            value={editedItem.quantity}
                                                            onChange={(e) =>
                                                                setEditedItem((prev) => ({
                                                                    ...prev,
                                                                    quantity: e.target.value,
                                                                }))
                                                            }
                                                            className="w-[80px] h-[35px] pl-2 border-2 border-[#FAF6ED] rounded no-spinner"
                                                        />
                                                    ) : (
                                                        item.quantity
                                                    )}
                                                </td>

                                                {/* Amount */}
                                                <td className="py-2 px-3 font-semibold">{item.amount}</td>

                                                {/* Activity */}
                                                <td className="py-2 px-3">
                                                    <div className="flex space-x-3">
                                                        {editIndex === index ? (
                                                            <>
                                                                <button
                                                                    onClick={() => {
                                                                        const updatedItems = [...items];
                                                                        updatedItems[editIndex] = editedItem;
                                                                        setItems(updatedItems);
                                                                        setEditIndex(null);
                                                                    }}
                                                                    className="text-green-600 text-sm"
                                                                >
                                                                    Save
                                                                </button>
                                                                <button
                                                                    onClick={() => setEditIndex(null)}
                                                                    className="text-red-600 text-sm"
                                                                >
                                                                    Cancel
                                                                </button>
                                                            </>
                                                        ) : (
                                                            <>
                                                                <button
                                                                    onClick={() => {
                                                                        setEditIndex(index);
                                                                        setEditedItem({ ...item });
                                                                    }}
                                                                >
                                                                    <img src={edit} alt="edit" className="w-4 h-4" />
                                                                </button>
                                                                <button
                                                                    onClick={() => {
                                                                        const updatedItems = items.filter((_, i) => i !== index);
                                                                        setItems(updatedItems);
                                                                    }}
                                                                >
                                                                    <img src={deleteIcon} alt="delete" className="w-4 h-4" />
                                                                </button>
                                                            </>
                                                        )}
                                                    </div>
                                                </td>
                                            </tr>
                                        ))}
                                    </tbody>
                                </table>
                            </div>
                            <div className="flex  border-[2px] border-opacity-25 rounded mt-2 lg:w-[451px] border-[#BF9853] lg:ml-[655px] text-sm">
                                <div className="grid grid-cols-4 divide-x divide-[#e6e1d1] lg:w-[451px] lg:h-[40px] text-center">
                                    <label className="py-2 font-semibold text-base">Total</label>
                                    <label className="py-2 font-semibold text-base"></label>
                                    <label className="py-2 font-semibold text-base"></label>
                                    <label className="py-2 font-semibold w-32 text-base"></label>
                                </div>
                            </div>

                            <div className="flex lg:justify-end gap-4 mt-4">
                                <button className="bg-[#BF9853] text-white w-[98px] h-[36px] px-6 rounded">Cancel</button>
                                <button className="bg-[#BF9853] text-white w-[137px] h-[36px] px-5 rounded">Generate PO</button>
                            </div>
                        </div>
                    </div>
                </div>
            )}
        </div>
    )
}

export default PurchaseOrder
